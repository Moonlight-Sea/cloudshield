package pers.sea.shield.dispatch.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sea.shield.dispatch.pojo.entity.ParamInfo;
import pers.sea.shield.dispatch.mapper.ParamInfoMapper;
import pers.sea.shield.dispatch.service.IParamInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 请求参数表 服务实现类
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
@Service
public class ParamInfoServiceImpl extends ServiceImpl<ParamInfoMapper, ParamInfo> implements IParamInfoService {

    @Override
    public Page<ParamInfo> listPage(int current, int size, ParamInfo paramInfo) {
        Page<ParamInfo> page = new Page<>(current, size);
        QueryWrapper<ParamInfo> queryWrapper = Wrappers.query(paramInfo).orderByDesc("create_time");
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<ParamInfo> listByApiUuid(String uuid) {
        if (uuid != null) {
            return this.baseMapper.selectList(Wrappers.<ParamInfo>query().lambda().eq(ParamInfo::getApiUuid, uuid));
        }
        return null;
    }
}
