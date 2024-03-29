package pers.sea.shield.batch.common.enums;

import pers.sea.shield.batch.common.constant.BatchSystemConstant;
import pers.sea.shield.common.core.constant.CloudShieldSystemConstant;
import pers.sea.shield.common.core.exception.ServiceErrorInfo;

/**
 * 批量模块运行时异常
 *
 * @author moon on 2023/11/22
 */
public enum BatchErrorInfo implements ServiceErrorInfo {

    // V - 验证异常
    // ------------------------------------------------------------------------------------------------------

    // B - 业务异常
    // ------------------------------------------------------------------------------------------------------

    JOB_NOT_FOUND("B001", "任务不存在"),

    // F - 文件异常
    // ------------------------------------------------------------------------------------------------------

    FILE_IS_EMPTY("F001", "文件不能为空"),
    FILE_NAME_INVALID("F002", "文件名不合法"),
    FILE_UPLOAD_FAILED("F003", "文件上传失败"),
    FILE_DOWNLOAD_FAILED("F004", "文件下载失败"),
    FILE_NOT_FOUND("F005", "文件不存在"),

    // N - 网络通讯错误
    // ------------------------------------------------------------------------------------------------------

    POST_REQUEST_ERROR("N001", "POST请求错误"),

    // S - 系统错误
    // ------------------------------------------------------------------------------------------------------

    SYSTEM_ERROR("S001", "系统错误"),


    ;

    private final String code;
    private final String message;

    BatchErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return String.format("E%s%s%s", CloudShieldSystemConstant.CLOUD_SHIELD_SYSTEM_CODE, BatchSystemConstant.SYSTEM_CODE, code);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
