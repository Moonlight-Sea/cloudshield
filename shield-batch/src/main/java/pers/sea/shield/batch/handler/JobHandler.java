package pers.sea.shield.batch.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.common.constant.BatchJobConstant;
import pers.sea.shield.batch.common.util.IPUtils;
import pers.sea.shield.batch.pojo.convert.JobConvert;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.pojo.entity.JobConfig;
import pers.sea.shield.batch.service.IJobConfigService;
import pers.sea.shield.batch.service.IJobService;
import pers.sea.shield.batch.task.SimpleJobTaskExecutor;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

import static pers.sea.shield.batch.common.constant.BatchJobConstant.STATUS_ENABLE;

/**
 * 批量作业业务处理类
 *
 * @author moon on 2023/11/27
 */
@Service
@Slf4j
public class JobHandler {

    private final IJobConfigService jobConfigService;
    private final IJobService jobService;
    private final SimpleJobTaskExecutor simpleJobTask;

    public JobHandler(IJobConfigService jobConfigService,
                      IJobService jobService,
                      SimpleJobTaskExecutor simpleJobTask) {
        this.jobConfigService = jobConfigService;
        this.jobService = jobService;
        this.simpleJobTask = simpleJobTask;
    }

    /**
     * 找到监控文件对应的任务配置，并创建任务，设置状态为等待
     *
     * @param flagFile 监控文件
     */
    public void findThenCreate(File flagFile) {
        LambdaQueryWrapper<JobConfig> queryWrapper = Wrappers.lambdaQuery(JobConfig.class)
                .eq(JobConfig::getStatus, STATUS_ENABLE);
        jobConfigService.list(queryWrapper).stream()
                .filter(config -> Pattern.compile(config.getMonitorFile()).matcher(flagFile.getName()).matches())
                .forEach(config -> jobService.save(JobConvert.convert(config, flagFile)));
    }

    @Async
    public void start() {
        // 1. 找到需要执行的任务
        List<Job> waitJob = jobService.findWaitJob(IPUtils.getIp(), LocalTime.now().toString());
        if (CollectionUtils.isEmpty(waitJob)) {
            log.info("无可执行作业");
        }

        // 2. 开始执行任务
        waitJob.forEach(job -> {
            // 2.0 任务是否允许执行
            if (!simpleJobTask.checkAllow(job)) {
                return;
            }
            // 2.1 开始执行前的准备工作
            simpleJobTask.setUp(job);
            // 2.2 执行任务
            try {
                simpleJobTask.execute(job);
            } catch (IOException e) {
                log.error("执行任务失败", e);
                job.setJobStatus(BatchJobConstant.JOB_STATUS_FAIL);
                jobService.updateById(job);
            }
            // 2.3 结束执行后的工作
            try {
                simpleJobTask.after(job);
            } catch (IOException e) {
                log.error("执行任务结束后工作失败", e);
                job.setJobStatus(BatchJobConstant.JOB_STATUS_FAIL);
                jobService.updateById(job);
            }
        });
    }

}
