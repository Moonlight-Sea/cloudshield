package pers.sea.shield.dispatch.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * 快速派遣服务
 *
 * @author moon on 6/13/2023
 */
@RestController
public class DispatchController {

    /**
     * 处理xml报文
     *
     * @param request ESB => XML报文信息
     * @return XML报文 => ESB
     */
    @PostMapping(value = "/api", produces = {MediaType.APPLICATION_XML_VALUE})
    public CommonResult<String> entrance(@RequestBody String request) {
        return CommonResult.ok(request);
    }

}
