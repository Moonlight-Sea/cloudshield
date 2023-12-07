package pers.sea.shield.batch.handler;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pers.sea.shield.batch.common.enums.BatchErrorInfo;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * RestAPI请求异常处理
 *
 * @author moon on 12/7/2023
 */
@RestControllerAdvice
@MapperScan(value = "")
@Slf4j
public class BatchRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public CommonResult<Object> handleAll(Exception ex, WebRequest request) {
        log.error("url: {}, exception: {}", request.getDescription(false), ex.getMessage(), ex);
        return CommonResult.failed(BatchErrorInfo.SYSTEM_ERROR);
    }
}
