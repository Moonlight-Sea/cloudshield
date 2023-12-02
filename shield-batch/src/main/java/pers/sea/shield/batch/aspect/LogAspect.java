package pers.sea.shield.batch.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import pers.sea.shield.batch.pojo.entity.Job;

/**
 * 日志记录
 *
 * @author moon on 2023/11/27
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @After(value = "execution(public * pers.sea.shield.batch.service.impl.JobServiceImpl.save(..)) && args(job)", argNames = "job")
    public void logRecord(Job job) {
        log.info("JobServiceImpl: 文件：{} 批量任务: {} 记录已生成", job.getMonitorFile(), job.getJobName());
    }

    @After(value = "execution(public * pers.sea.shield.batch.service.IJobConfigService.save(..)) && args(job)", argNames = "job")
    public void jobSaveLog(Job job) {
        log.info("IJobService: 文件：{} 批量任务: {} 记录已生成", job.getMonitorFile(), job.getJobName());
    }
}
