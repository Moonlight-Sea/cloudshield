package pers.sea.shield.batch.common.constant;

/**
 * 批量作业魔法值常量
 *
 * @author moon on 2023/11/27
 */
public class BatchJobConstant {

    // 状态: 0-未启用;1-启用;
    // ------------------------------------------------------------------------------------------------------
    public static final String STATUS_ENABLE = "1";


    // 00-未执行;01-执行中;02-执行成功;03-执行失败;04-推送中;05-推送失败;06-已取消
    // ------------------------------------------------------------------------------------------------------
    public static final String JOB_STATUS_WAITING = "00";
    public static final String JOB_STATUS_RUNNING = "01";
    public static final String JOB_STATUS_SUCCESS = "02";
    public static final String JOB_STATUS_FAIL = "03";
    public static final String JOB_STATUS_PUSHING = "04";
    public static final String JOB_STATUS_PUSH_FAIL = "05";
    public static final String JOB_STATUS_CANCEL = "06";
}
