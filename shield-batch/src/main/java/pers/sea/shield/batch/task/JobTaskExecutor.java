package pers.sea.shield.batch.task;

import pers.sea.shield.batch.pojo.entity.Job;

import java.io.IOException;

/**
 * @author moon on 12/1/2023
 */
public interface JobTaskExecutor {

    /**
     * 校验是否允许执行
     *
     * @param job 任务
     * @return true:允许执行 false:不允许执行
     */
    boolean checkAllow(Job job);

    /**
     * 任务执行前: 如：记录任务开始时间
     *
     * @param job 任务
     */
    void setUp(Job job);

    /**
     * 任务执行
     *
     * @param job 任务
     */
    void execute(Job job) throws IOException;

    /**
     * 任务执行后
     *
     * @param job 任务
     */
    void after(Job job) throws IOException;

}
