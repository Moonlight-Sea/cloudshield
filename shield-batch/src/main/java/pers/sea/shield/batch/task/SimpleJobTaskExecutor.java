package pers.sea.shield.batch.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.common.constant.BatchJobConstant;
import pers.sea.shield.batch.common.constant.BatchProperty;
import pers.sea.shield.batch.common.enums.BatchErrorInfo;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.pojo.entity.JobConfig;
import pers.sea.shield.batch.service.IJobConfigService;
import pers.sea.shield.batch.service.IJobService;
import pers.sea.shield.common.core.constant.DateConstant;
import pers.sea.shield.common.core.exception.CloudShieldException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 批量作业执行类
 *
 * @author moon on 2023/11/29
 */
@Service
@Slf4j
public class SimpleJobTaskExecutor implements JobTaskExecutor {

    private final IJobService jobService;
    private final IJobConfigService jobConfigService;
    private final ObjectMapper objectMapper;
    private File loadFile;
    private File resultFile;

    public SimpleJobTaskExecutor(IJobService jobService, IJobConfigService jobConfigService, ObjectMapper objectMapper) {
        this.jobService = jobService;
        this.jobConfigService = jobConfigService;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isAllow(Job job) {
        log.debug("isAllow execute");
        // 1. 检查监控标识文件是否存在
        String monitorFile = job.getMonitorFile();
        return Paths.get(monitorFile).toFile().exists();
    }

    @Override
    public void before(Job job) {
        log.debug("before execute");
        // 1. 加载作业配置
        JobConfig jobConfig = jobConfigService.getById(job.getConfigId());
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_WITHOUT_TIME));
        // 2. 加载数据文件
        loadFile = Paths.get(BatchProperty.monitorDir, date).toFile();
        if (!loadFile.exists()) {
            // 加载数据文件不存在 结束
            return;
        }
        job.setLoadFile(loadFile.getAbsolutePath());

        // 3. 获取作业结果文件
        String baseResultDir = Paths.get(BatchProperty.resultDir, date).toString();
        resultFile = Paths.get(baseResultDir, jobConfig.getResultFile()).toFile();
        // 重复作业删除上一次的结果文件
        if (resultFile.exists()) {
            resultFile.delete();
        }
    }

    @Override
    public void execute(Job job) throws IOException {

        if (loadFile == null || !loadFile.exists()) {
            log.error("{} - {} the data file not found", job.getId(), job.getJobName(), new CloudShieldException(BatchErrorInfo.FILE_NOT_FOUND));
            job.setJobStatus(BatchJobConstant.JOB_STATUS_PUSH_FAIL);
            job.setDescription(BatchErrorInfo.FILE_NOT_FOUND.getMessage());
            jobService.updateById(job);
            return;
        }

        // 1. 执行作业
        // 成功计数
        AtomicInteger count = new AtomicInteger(0);
        try (LineIterator lineIterator = FileUtils.lineIterator(loadFile, StandardCharsets.UTF_8.displayName())) {
            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine();
                // 2. 处理数据
                JsonNode param = assembleParams(line);
                if (param == null) {
                    // 异常参数
                    continue;
                }

                // 2. 发送请求
                JsonNode result = postRequest(param);

                String resultStr = formatResult(result);

                if (resultStr != null) {
                    // 写入指定文件
                    FileUtils.write(resultFile, resultStr, StandardCharsets.UTF_8.displayName(), true);
                }

                // 3. 保存结果
                count.incrementAndGet();
            }
        }
        // 2. 保存结果

    }

    @Override
    public void after(Job job) {
        // TODO
        // 5. (option)推送结果至远程服务器
        // 6. 修改状态为完成 或 失败
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
        if (line != null) {
            try {
                return objectMapper.readTree(line);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        // TODO 根据批量作业配置的表达式来组装参数
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
        // TODO
        return null;
    }


    private String formatResult(JsonNode result) {
        log.debug("formatResult execute");
        // TODO
        return null;
    }


}
