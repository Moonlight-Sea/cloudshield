package pers.sea.shield.dispatch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sea.shield.dispatch.pojo.entity.ParamInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 请求参数表 服务类
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
public interface IParamInfoService extends IService<ParamInfo> {

    Page<ParamInfo> listPage(int current, int size, ParamInfo paramInfo);
}
