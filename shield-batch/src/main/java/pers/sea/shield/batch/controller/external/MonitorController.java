package pers.sea.shield.batch.controller.external;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pers.sea.shield.batch.service.IMonitorService;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * 目录监听控制器
 *
 * @author moon on 2023/11/28
 */
public class MonitorController {

    private final IMonitorService monitorService;

    public MonitorController(IMonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping("/monitor/{path}")
    public CommonResult<String> addMonitor(@PathVariable String path) {
        monitorService.addObserver(path);
        return CommonResult.ok();
    }

    @DeleteMapping("/monitor/{path}")
    public CommonResult<String> stopMonitor(@PathVariable String path) {
        monitorService.stopObserver(path);
        return CommonResult.ok();
    }
}
