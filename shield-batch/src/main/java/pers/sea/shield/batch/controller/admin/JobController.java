package pers.sea.shield.batch.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.service.IJobService;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author moon
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/job")
public class JobController {

    private final IJobService jobService;

    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/list/{current}/{size}")
    public CommonResult<Page<Job>> listPage(@PathVariable int current,
                                            @PathVariable int size) {
        Page<Job> pageInfo = new Page<>(current, size);
        Page<Job> jobPage = jobService.page(pageInfo);
        return CommonResult.ok(jobPage);
    }

    @GetMapping("/{id}")
    public CommonResult<Job> getJob(@PathVariable Integer id) {
        return CommonResult.ok(jobService.getById(id));
    }


}
