package pers.sea.shield.dispatch.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.sea.shield.dispatch.mapper.ApiInfoMapper;
import pers.sea.shield.dispatch.pojo.entity.ApiInfo;
import pers.sea.shield.dispatch.service.IApiInfoService;

/**
 * <p>
 * 接口表 服务实现类
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo> implements IApiInfoService {

    @Override
    public Page<ApiInfo> listPage(int current, int size, ApiInfo apiInfo) {
        Page<ApiInfo> page = new Page<>(current, size);
        QueryWrapper<ApiInfo> wrapper = Wrappers.query(apiInfo).orderByDesc("create_time");
        return this.baseMapper.selectPage(page, wrapper);
    }

    @Override
    public ApiInfo getApiInfoByItemCode(String itemCode) {
        return this.baseMapper.selectOne(Wrappers.lambdaQuery(ApiInfo.class).eq(ApiInfo::getItemCode, itemCode));
    }
}
