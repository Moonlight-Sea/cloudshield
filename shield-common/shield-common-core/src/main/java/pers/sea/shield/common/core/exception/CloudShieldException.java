package pers.sea.shield.common.core.exception;

import lombok.Getter;

/**
 * 系统异常
 *
 * @author moon on 6/14/2023
 */
@Getter
public class CloudShieldException extends RuntimeException {

    private final String code;

    public CloudShieldException(ServiceErrorInfo error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public CloudShieldException(ServiceErrorInfo error, String message) {
        super(message);
        this.code = error.getCode();
    }

    public CloudShieldException(ServiceErrorInfo error, String message, Throwable cause) {
        super(message, cause);
        this.code = error.getCode();
    }
}
