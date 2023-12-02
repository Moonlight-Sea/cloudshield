package pers.sea.shield.batch.pojo.convert;

import pers.sea.shield.batch.common.constant.BatchJobConstant;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.pojo.entity.JobConfig;

import java.io.File;

/**
 * JobRecord构建帮助类
 *
 * @author moon on 2023/11/27
 */
public class JobConvert {

    public static Job convert(JobConfig jobConfig, File file) {

        Job job = new Job();
        job.setConfigId(jobConfig.getId());
        job.setJobName(jobConfig.getName());
        job.setJobStatus(BatchJobConstant.JOB_STATUS_WAITING);
        job.setMonitorFile(file.getAbsolutePath());
        job.setLoadFile(jobConfig.getLoadFile());
        job.setAllowIp(jobConfig.getAllowIp());
        return job;

    }
}
