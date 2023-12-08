package pers.sea.shield.batch.mapper;

import org.apache.ibatis.annotations.Param;
import pers.sea.shield.batch.pojo.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author moon
 * @since 2023-12-08
 */
public interface JobMapper extends BaseMapper<Job> {

    List<Job> findWaitJob(@Param("nowTime") String nowTime,
                          @Param("ip") String ip);
}
