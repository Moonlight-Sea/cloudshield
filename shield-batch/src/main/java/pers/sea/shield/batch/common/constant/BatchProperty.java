package pers.sea.shield.batch.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * BatchProperty 配置项;
 *
 * @author moon on 2023/11/20
 */
@Configuration
public class BatchProperty {

    /**
     * 监控间隔;
     */
    public static Long monitorInterval;
    /**
     * 监控目录; 多个目录用逗号分隔; 每个目录结尾用 / 号;
     */
    public static String monitorDir;
    /**
     * 文件标识后缀;
     */
    public static String fileFlagSuffix;
    /**
     * 结果目录; 目录结尾用 / 号;
     */
    public static String resultDir;

    // 定时任务作业线程池 start
    // ------------------------------------------------------------------------------------------------------

    /**
     * 定时任务线程池名称;
     */
    public static String scheduledPoolName;
    /**
     * 定时任务线程池大小;
     */
    public static Integer scheduledPoolSize;

    // 定时任务作业线程池 end
    // ------------------------------------------------------------------------------------------------------

    //  @Async 配置 start
    // ------------------------------------------------------------------------------------------------------

    public static String asyncPoolName;
    /**
     * 异步作业线程池核心线程数;
     */
    public static Integer asyncCorePoolSize;
    /**
     * 异步作业线程池最大线程数;
     */
    public static Integer asyncMaxPoolSize;
    /**
     * 异步作业线程池队列容量;
     */
    public static Integer asyncQueueCapacity;
    /**
     * 异步作业线程池线程存活时间;
     */
    public static Integer asyncKeepAliveTime;

    // @Async 配置 end
    // ------------------------------------------------------------------------------------------------------

    @Value("${batch.monitor.interval:1000}")
    public void setMonitorInterval(Long monitorInterval) {
        BatchProperty.monitorInterval = monitorInterval;
    }

    @Value("${batch.monitor.dir}")
    public void setMonitorDir(String monitorDir) {
        BatchProperty.monitorDir = monitorDir;
    }

    @Value("${batch.file.flag.suffix:flag}")
    public void setFileFlagSuffix(String fileFlagSuffix) {
        BatchProperty.fileFlagSuffix = fileFlagSuffix;
    }

    @Value("${batch.result.dir}")
    public void setResultDir(String resultDir) {
        BatchProperty.resultDir = resultDir;
    }

    @Value("${batch.scheduled.pool.name:scheduledPool}")
    public void setScheduledPoolName(String scheduledPoolName) {
        BatchProperty.scheduledPoolName = scheduledPoolName;
    }

    @Value("${batch.scheduled.pool.size:5}")
    public void setScheduledPoolSize(Integer scheduledPoolSize) {
        BatchProperty.scheduledPoolSize = scheduledPoolSize;
    }

    @Value("${batch.async.pool.name:asyncPool}")
    public void setAsyncPoolName(String asyncPoolName) {
        BatchProperty.asyncPoolName = asyncPoolName;
    }

    @Value("${batch.async.core.pool.size:5}")
    public void setAsyncCorePoolSize(Integer asyncCorePoolSize) {
        BatchProperty.asyncCorePoolSize = asyncCorePoolSize;
    }

    @Value("${batch.async.max.pool.size:10}")
    public void setAsyncMaxPoolSize(Integer asyncMaxPoolSize) {
        BatchProperty.asyncMaxPoolSize = asyncMaxPoolSize;
    }

    @Value("${batch.async.queue.capacity:100}")
    public void setAsyncQueueCapacity(Integer asyncQueueCapacity) {
        BatchProperty.asyncQueueCapacity = asyncQueueCapacity;
    }

    @Value("${batch.async.keep.alive.time:60}")
    public void setAsyncKeepAliveTime(Integer asyncKeepAliveTime) {
        BatchProperty.asyncKeepAliveTime = asyncKeepAliveTime;
    }

}
