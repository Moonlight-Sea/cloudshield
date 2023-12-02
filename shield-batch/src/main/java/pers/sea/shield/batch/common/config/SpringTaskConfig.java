package pers.sea.shield.batch.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import pers.sea.shield.batch.common.constant.BatchProperty;

/**
 * <p>@Schedule 定时作业线程池配置
 *
 * @author moon on 2023/11/27
 */
@Configuration
@EnableScheduling
public class SpringTaskConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 创建一个线程池任务调度器
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 设置线程名的前缀
        taskScheduler.setThreadNamePrefix(BatchProperty.scheduledPoolName);
        // 设置线程池的大小
        taskScheduler.setPoolSize(BatchProperty.scheduledPoolSize);
        // 初始化线程池任务调度器
        taskScheduler.initialize();
        // 将线程池任务调度器设置给任务注册器
        taskRegistrar.setTaskScheduler(taskScheduler);
    }


}
