package pers.sea.shield.batch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.sea.shield.batch.mapper.JobMapper;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.service.IJobService;

import java.util.List;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
@Service("jobService")
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    @Override
    public List<Job> findWaitJob(String nowTime, String ip) {
        return baseMapper.findWaitJob(nowTime, ip);
    }
}
