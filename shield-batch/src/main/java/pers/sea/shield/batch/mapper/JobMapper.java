package pers.sea.shield.batch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pers.sea.shield.batch.pojo.entity.Job;

import java.util.List;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 查询等待执行的任务
     *
     * @param nowTime 当前时间: HH:mm:ss
     * @param ip      IP地址
     * @return List of Job which is waiting to be executed.
     */
    List<Job> findWaitJob(@Param(value = "nowTime") String nowTime,
                          @Param(value = "ip") String ip);
}
