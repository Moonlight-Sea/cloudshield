package pers.sea.shield.batch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.sea.shield.batch.pojo.entity.Job;

import java.util.List;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
public interface IJobService extends IService<Job> {

    /**
     * 查询等待执行的可执行任务
     *
     * @param nowTime 当前时间 pattern: HH:mm:ss
     * @param ip      客户端ip
     * @return List of job which is waiting to be executed
     */

    List<Job> findWaitJob(String nowTime, String ip);
}
