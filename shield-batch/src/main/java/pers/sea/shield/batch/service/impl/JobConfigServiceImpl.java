package pers.sea.shield.batch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.mapper.JobConfigMapper;
import pers.sea.shield.batch.pojo.entity.JobConfig;
import pers.sea.shield.batch.service.IJobConfigService;

/**
 * <p>
 * 任务公共配置表,保存复用性高的配置文件 服务实现类
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
@Service
public class JobConfigServiceImpl extends ServiceImpl<JobConfigMapper, JobConfig> implements IJobConfigService {

}
