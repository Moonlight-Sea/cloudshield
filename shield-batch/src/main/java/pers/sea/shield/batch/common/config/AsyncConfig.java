package pers.sea.shield.batch.common.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pers.sea.shield.batch.common.constant.BatchProperty;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>@Async 线程池 AsyncConfigurer
 *
 * @author moon on 2023/11/29
 */
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池名前缀
        executor.setThreadNamePrefix(BatchProperty.asyncPoolName);
        // 核心线程数
        executor.setCorePoolSize(BatchProperty.asyncCorePoolSize);
        // 最大线程数
        executor.setMaxPoolSize(BatchProperty.asyncMaxPoolSize);
        // 队列容量
        executor.setQueueCapacity(BatchProperty.asyncQueueCapacity);
        // 线程池维护线程所允许的空闲时间
        executor.setKeepAliveSeconds(BatchProperty.asyncKeepAliveTime);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化; 这一步千万不能忘了，否则报错： java.lang.IllegalStateException: ThreadPoolTaskExecutor not initialized
        executor.initialize();
        return executor;
    }

    /**
     * 异步异常处理
     *
     * @return 自定义异常处理器
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
