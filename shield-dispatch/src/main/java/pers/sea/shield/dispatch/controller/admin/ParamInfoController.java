package pers.sea.shield.dispatch.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pers.sea.shield.common.core.pojo.CommonResult;
import pers.sea.shield.dispatch.pojo.entity.ParamInfo;
import pers.sea.shield.dispatch.service.IParamInfoService;

/**
 * 参数映射
 *
 * @author moon on 12/12/2023
 */
@RestController
@RequestMapping("/param")
public class ParamInfoController {

    private final IParamInfoService paramService;

    public ParamInfoController(IParamInfoService paramService) {
        this.paramService = paramService;
    }

    @RequestMapping("/list/{current}/{size}")
    public CommonResult<Page<ParamInfo>> listPage(@PathVariable int current,
                                                  @PathVariable int size,
                                                  ParamInfo paramInfo) {
        return CommonResult.ok(paramService.listPage(current, size, paramInfo));
    }

    @GetMapping("/info")
    public CommonResult<Boolean> save(@RequestBody ParamInfo paramInfo) {
        return CommonResult.ok(paramService.save(paramInfo));
    }

    @PutMapping("/info")
    public CommonResult<Boolean> update(@RequestBody ParamInfo paramInfo) {
        return CommonResult.ok(paramService.updateById(paramInfo));
    }

    @DeleteMapping("/info/{id}")
    public CommonResult<Boolean> delete(@PathVariable String id) {
        return CommonResult.ok(paramService.removeById(id));
    }
}
