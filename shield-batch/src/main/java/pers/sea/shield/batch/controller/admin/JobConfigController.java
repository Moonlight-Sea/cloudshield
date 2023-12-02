package pers.sea.shield.batch.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pers.sea.shield.batch.pojo.entity.JobConfig;
import pers.sea.shield.batch.service.IJobConfigService;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * <p>
 * 任务公共配置表,保存复用性高的配置文件 前端控制器
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/job/config")
public class JobConfigController {

    private final IJobConfigService jobConfigService;

    public JobConfigController(IJobConfigService jobConfigService) {
        this.jobConfigService = jobConfigService;
    }

    @GetMapping("/list/{current}/{size}")
    public CommonResult<Page<JobConfig>> listPage(@PathVariable int current,
                                                  @PathVariable int size) {
        Page<JobConfig> pageInfo = new Page<>(current, size);
        Page<JobConfig> page = jobConfigService.page(pageInfo);
        return CommonResult.ok(page);
    }

    @PostMapping("/")
    public CommonResult<String> save(@RequestBody JobConfig config) {
        jobConfigService.save(config);
        return CommonResult.ok();
    }

    @PutMapping("/")
    public CommonResult<String> update(@RequestBody JobConfig config) {
        jobConfigService.updateById(config);
        return CommonResult.ok();
    }

    @DeleteMapping("/{id}")
    public CommonResult<String> delete(@PathVariable Integer id) {
        jobConfigService.removeById(id);
        return CommonResult.ok();
    }


}
