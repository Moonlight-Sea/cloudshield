package pers.sea.shield.batch.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.sea.shield.batch.handle.JobHandle;

/**
 * 批量作业调度
 *
 * @author moon on 2023/03/18
 */
@Component
public class JobSchedule {

    private final JobHandle jobHandle;

    public JobSchedule(JobHandle jobHandle) {
        this.jobHandle = jobHandle;
    }

    /**
     * 早上8点之后 每隔5分钟执行1次
     */
    @Scheduled(cron = "0 0/5 8 * * ?")
    public void schedule() {
        // 定时任务
        jobHandle.start();
    }
}
