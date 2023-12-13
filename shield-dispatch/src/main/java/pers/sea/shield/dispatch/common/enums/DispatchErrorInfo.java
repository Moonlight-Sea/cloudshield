package pers.sea.shield.dispatch.common.enums;

import pers.sea.shield.common.core.constant.CloudShieldSystemConstant;
import pers.sea.shield.common.core.exception.ServiceErrorInfo;
import pers.sea.shield.dispatch.common.constant.DispatchSystemConstant;

/**
 * Dispatch exception enums
 *
 * @author moon on 12/13/2023
 */
public enum DispatchErrorInfo implements ServiceErrorInfo {

    // V - 验证异常
    // ------------------------------------------------------------------------------------------------------
    PARAM_XML_INVALID("V001", "请求参数XML格式不正确"),
    PARAM_INVALID("V002", "请求参数不合法"),
    PARAM_NECESSARY_MISSING("V003", "必输请求参数缺失"),


    // B - 业务异常
    // ------------------------------------------------------------------------------------------------------
    API_INFO_NOT_FOUND("B001", "API信息不存在"),

    ;

    private final String code;
    private final String message;



    DispatchErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return String.format("E%s%s%s", CloudShieldSystemConstant.CLOUD_SHIELD_SYSTEM_CODE, DispatchSystemConstant.SYSTEM_CODE, code);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
