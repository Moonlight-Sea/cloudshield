package pers.sea.shield.common.core.exception;

import static pers.sea.shield.common.core.constant.CloudShieldSystemConstant.CLOUD_SHIELD_SYSTEM_CODE;

/**
 * 公用返回
 *
 * @author moon on 2023/11/22
 */
public enum GlobalServiceErrorInfo implements ServiceErrorInfo {

    OK("S000", "OK"),


    ;

    private final String code;
    private final String message;

    GlobalServiceErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return String.format("E%s000%s", CLOUD_SHIELD_SYSTEM_CODE, code);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
