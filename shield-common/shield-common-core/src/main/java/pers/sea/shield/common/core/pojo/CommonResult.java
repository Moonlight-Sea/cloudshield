package pers.sea.shield.common.core.pojo;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import pers.sea.shield.common.core.exception.GlobalServiceErrorInfo;
import pers.sea.shield.common.core.exception.ServiceErrorInfo;

/**
 * 全局统一响应
 *
 * @author moon on 6/14/2023
 */
@Setter
@Getter
public class CommonResult<T> {

    private String code;
    private String message;
    private T data;

    private CommonResult(ServiceErrorInfo errorInfo, T data) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
        this.data = data;
    }

    public CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> status(ServiceErrorInfo errorInfo, T data) {
        return new CommonResult<>(errorInfo, data);
    }

    public static <T> CommonResult<T> ok() {
        return status(GlobalServiceErrorInfo.OK, null);
    }

    public static <T> CommonResult<T> ok(T data) {
        return status(GlobalServiceErrorInfo.OK, data);
    }

    public static <T> CommonResult<T> failed(ServiceErrorInfo errorInfo) {
        return new CommonResult<>(errorInfo.getCode(), errorInfo.getMessage(), null);
    }

    @Override
    public String toString() {
        // toJSONString()
        return JSONUtil.toJsonStr(this);
    }

}
