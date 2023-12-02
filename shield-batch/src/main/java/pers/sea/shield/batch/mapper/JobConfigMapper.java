package pers.sea.shield.batch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.sea.shield.batch.pojo.entity.JobConfig;

/**
 * <p>
 * 任务公共配置表,保存复用性高的配置文件 Mapper 接口
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
@Mapper
public interface JobConfigMapper extends BaseMapper<JobConfig> {

}
