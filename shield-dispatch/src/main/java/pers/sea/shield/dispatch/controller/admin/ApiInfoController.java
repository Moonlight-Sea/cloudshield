package pers.sea.shield.dispatch.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pers.sea.shield.common.core.pojo.CommonResult;
import pers.sea.shield.dispatch.pojo.entity.ApiInfo;
import pers.sea.shield.dispatch.service.IApiInfoService;

/**
 * 接口信息接口服务
 *
 * @author moon on 12/12/2023
 */
@RestController
@RequestMapping("/api")
public class ApiInfoController {

    private final IApiInfoService apiInfoService;

    public ApiInfoController(IApiInfoService apiInfoService) {
        this.apiInfoService = apiInfoService;
    }


    @PostMapping("/info")
    public CommonResult<Boolean> save(@RequestBody ApiInfo apiInfo) {
        return CommonResult.ok(apiInfoService.save(apiInfo));
    }

    @PutMapping("/info")
    public CommonResult<Boolean> update(@RequestBody ApiInfo apiInfo) {
        return CommonResult.ok(apiInfoService.updateById(apiInfo));
    }

    @DeleteMapping("/info/{id}")
    public CommonResult<Boolean> delete(@PathVariable String id) {
        return CommonResult.ok(apiInfoService.removeById(id));
    }

    @GetMapping("/info/{id}")
    public CommonResult<ApiInfo> get(@PathVariable String id) {
        return CommonResult.ok(apiInfoService.getById(id));
    }

    @GetMapping("/info/list/{current}/{size}")
    public CommonResult<Page<ApiInfo>> listPage(@PathVariable int current,
                                                @PathVariable int size,
                                                ApiInfo apiInfo) {
        return CommonResult.ok(apiInfoService.listPage(current, size, apiInfo));
    }
}
