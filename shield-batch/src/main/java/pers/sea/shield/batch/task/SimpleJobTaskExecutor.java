package pers.sea.shield.batch.task;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.common.constant.BatchJobConstant;
import pers.sea.shield.batch.common.constant.BatchProperty;
import pers.sea.shield.batch.common.enums.BatchErrorInfo;
import pers.sea.shield.batch.common.util.SSHJUtil;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.pojo.entity.JobConfig;
import pers.sea.shield.batch.service.IJobConfigService;
import pers.sea.shield.batch.service.IJobService;
import pers.sea.shield.common.core.constant.DateConstant;
import pers.sea.shield.common.core.constant.SymbolConstant;
import pers.sea.shield.common.core.exception.CloudShieldException;
import pers.sea.shield.common.core.util.AviatorScriptUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 批量作业执行类
 * executor order:
 * 1. check {@link #checkAllow(Job)}}
 * 2. init {@link #setUp(Job)}
 * 3. execute {@link #execute(Job)}
 * 4. after {@link #after(Job)}
 *
 * @author moon on 2023/11/29
 */
@Service
@Slf4j
public class SimpleJobTaskExecutor implements JobTaskExecutor {

    private final IJobService jobService;
    private final IJobConfigService jobConfigService;
    private final ObjectMapper objectMapper;

    // process content
    // ------------------------------------------------------------------------------------------------------

    private JobConfig jobConfig;
    private File loadFile;
    private File resultFile;

    public SimpleJobTaskExecutor(IJobService jobService, IJobConfigService jobConfigService, ObjectMapper objectMapper) {
        this.jobService = jobService;
        this.jobConfigService = jobConfigService;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean checkAllow(Job job) {
        log.debug("isAllow execute");
        // 1. 检查监控标识文件是否存在
        String monitorFile = job.getMonitorFile();
        return Paths.get(monitorFile).toFile().exists();
    }

    @Override
    public void setUp(Job job) {
        log.debug("before execute");
        // 1. 加载作业配置
        jobConfig = jobConfigService.getById(job.getConfigId());
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_WITHOUT_TIME));
        // 2. 加载数据文件
        loadFile = Paths.get(BatchProperty.monitorDir, date, jobConfig.getLoadFile()).toFile();
        if (!loadFile.exists()) {
            // 加载数据文件不存在 结束
            return;
        }
        job.setLoadFile(loadFile.getAbsolutePath());

        // 3. 获取作业结果文件
        String baseResultDir = Paths.get(BatchProperty.resultDir, date).toString();
        resultFile = Paths.get(baseResultDir, jobConfig.getResultFile()).toFile();
        // 重复作业删除上一次的结果文件
        resultFile.deleteOnExit();
    }

    @Override
    public void execute(Job job) throws IOException {

        if (loadFile == null || !loadFile.exists()) {
            log.error("id: [{}] - name: [{}] - loadFile: [{}] that data file not found", job.getId(), job.getJobName(), job.getLoadFile(), new CloudShieldException(BatchErrorInfo.FILE_NOT_FOUND));
            job.setJobStatus(BatchJobConstant.JOB_STATUS_PUSH_FAIL);
            job.setDescription(BatchErrorInfo.FILE_NOT_FOUND.getMessage());
            jobService.updateById(job);
            return;
        }

        // 1. 执行作业
        // 成功计数
        AtomicInteger okCount = new AtomicInteger(0);
        AtomicInteger failedCount = new AtomicInteger(0);
        AtomicInteger doneCount = new AtomicInteger(0);
        List<String> resultList = new ArrayList<>();
        try (LineIterator lineIterator = FileUtils.lineIterator(loadFile, StandardCharsets.UTF_8.displayName())) {
            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine();
                log.debug("id: [{}] - name: [{}] - loadFile: [{}] - line: [{}]", job.getId(), job.getJobName(), job.getLoadFile(), line);

                // 1.1. 处理数据
                JsonNode param = assembleParams(line);
                if (param == null) {
                    // 异常参数, 错误计数+1
                    failedCount.incrementAndGet();
                    continue;
                }

                // 1.2. 发送请求
                JsonNode result = postRequest(param);
                if (result == null) {
                    failedCount.incrementAndGet();
                    log.error("id: [{}] - name: [{}] - param: [{}] - result: [{}] - post request error", job.getId(), job.getJobName(), param, result, new CloudShieldException(BatchErrorInfo.POST_REQUEST_ERROR));
                    continue;
                }

                // 1.3. 格式化结果
                String resultStr = formatResult(result);
                if (StringUtils.isBlank(resultStr)) {
                    // 异常结果, 错误计数+1
                    failedCount.incrementAndGet();
                    continue;
                }

                // 1.4 更新数据和结果文件
                resultList.add(resultStr);
                if (resultList.size() % BatchJobConstant.REFRESH_CACHE_LIMIT == 0) {
                    // 刷新缓存
                    FileUtils.writeLines(resultFile, resultList, true);
                    // 清空缓存
                    resultList.clear();
                }
            }
        }

        if (!resultList.isEmpty()) {
            // 刷新缓存
            FileUtils.writeLines(resultFile, resultList, true);
        }

        // 2. 保存结果
        job.setJobStatus(BatchJobConstant.JOB_STATUS_SUCCESS);
        job.setSuccessCount(okCount.get());
        job.setFailCount(failedCount.get());
        job.setDoneCount(doneCount.get());
        jobService.updateById(job);

    }

    @Override
    public void after(Job job) throws IOException {
        // 5. (option)推送结果至远程服务器
        if (!job.getJobStatus().equals(BatchJobConstant.JOB_STATUS_SUCCESS)) {
            return;
        }
        if (StringUtils.isBlank(jobConfig.getRemoteServerIp())) {
            return;
        }

        // 1. get local file
        String localFile = job.getResultFile();
        SSHClient sshClient = SSHJUtil.setupSshj(jobConfig.getRemoteServerIp(), jobConfig.getRemoteServerUser(), jobConfig.getRemoteServerPassword());
        SFTPClient sftpClient = sshClient.newSFTPClient();
        // 2. put local file
        sftpClient.put(localFile, jobConfig.getRemoteServerPath());
        // 3. close
        sftpClient.close();
        sshClient.disconnect();
    }

    // private methods
    // ------------------------------------------------------------------------------------------------------


    /**
     * 根据批量作业配置的表达式来组装参数
     *
     * @param line the data file line
     * @return JsonNode the assembled params
     */
    private JsonNode assembleParams(String line) {
        log.debug("assembleParam exec");
        if (StringUtils.isBlank(line)) {
            return null;
        }

        log.debug("line: [{}]", line);
        String[] dataArr = line.split(jobConfig.getColumnMark());
        String[] columnArr = jobConfig.getColumnName().split(SymbolConstant.COMMA);
        if (dataArr.length != columnArr.length) {
            log.debug("数据文件的长度和配置的列长度不一致");
            return null;
        }
        ObjectNode env = objectMapper.createObjectNode();
        for (int i = 0; i < dataArr.length; i++) {
            String data = dataArr[i];
            String column = columnArr[i];
            env.put(column, data);
        }

        try {
            // 根据批量作业配置的表达式来组装参数
            return objectMapper.readTree(AviatorScriptUtil.executeWithCache(jobConfig.getApiParams(), env).toString());
        } catch (JsonProcessingException e) {
            log.error("批量作业配置转换异常: {}", env.toPrettyString(), e);
        }
        return null;
    }

    /**
     * 发送请求
     *
     * @param param 参数
     * @return response of post request
     */
    private JsonNode postRequest(JsonNode param) {
        log.debug("postRequest execute");
        if (param == null) {
            return null;
        }
        String url = jobConfig.getApiUrl();
        String result = HttpUtil.post(url, param.toString());
        try {
            return objectMapper.readTree(result);
        } catch (JsonProcessingException e) {
            log.error("the url: {} post param: {} response 转换异常: {}", url, param.toPrettyString(), result, e);
        }
        return null;
    }


    private String formatResult(JsonNode result) {
        log.debug("formatResult execute");
        if (result == null) {
            return null;
        }
        String formatFunc = jobConfig.getFormatFunc();
        switch (formatFunc) {
            case BatchJobConstant.FORMAT_FUNCTION_EXPRESS -> {
                try {
                    return AviatorScriptUtil.executeWithCache(formatFunc, result).toString();
                } catch (Exception e) {
                    log.error("批量作业配置转换异常: {}", result.toPrettyString(), e);
                }
            }
            case BatchJobConstant.FORMAT_FUNCTION_JAR -> {
                // TODO 调用 jar 格式化
            }
            default -> {
                return result.toPrettyString();
            }
        }
        return null;
    }


}
