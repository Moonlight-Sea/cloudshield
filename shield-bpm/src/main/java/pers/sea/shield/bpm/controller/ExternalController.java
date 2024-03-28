package pers.sea.shield.bpm.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * 外部接口服务
 *
 * @author moon on 2024/3/26 by idea
 */
@RequestMapping("/api")
@RestController
public class ExternalController {


    /**
     * 启动流程-异步返回
     *
     * @return CommonResult of JsonNode
     */
    @PostMapping("/start/async/")
    public CommonResult<JsonNode> startFlowAsync() {
        // TODO start flow async
        return CommonResult.ok();
    }
}
