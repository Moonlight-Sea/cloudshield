package pers.sea.shield.dispatch.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.sea.shield.dispatch.pojo.entity.ApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口表 服务类
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
public interface IApiInfoService extends IService<ApiInfo> {

    /**
     * 分页查询
     *
     * @param current 当前页
     * @param size    每页大小
     * @param apiInfo 查询条件
     * @return 分页结果
     */
    Page<ApiInfo> listPage(int current, int size, ApiInfo apiInfo);
}
