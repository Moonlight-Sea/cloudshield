package pers.sea.shield.common.core.exception;

/**
 * 业务异常的错误码区间, 解决：解决各模块错误码定义, 避免重复, 在此只声明不做实际使用
 * <p>
 * 一共11位, 分为5段
 * <p>交易错误: 类型(1位数字) - 系统编号(3位数字) - 微服务模块编号(3位字母) - 错误分类(1位字母)  - 错误码顺位号(3位数字)
 * <p>
 * 类型:
 * E - 业务级别异常
 * X - 预留
 * <p>
 * 系统编号:
 * 001 - 前置系统
 * 002 - 商品系统
 * <p>
 * 微服务模块编号:
 * 不限制规则。一般建议, 每个系统里面, 可能有多个模块, 可以再去做分段。以用户系统为例子：
 * 001 - Dispatch 模块
 * 002 - User 模块
 * 003 - MobileCode 模块
 * <p>错误分类:
 * <p>V-参数校验问题 validate
 * <p>B-业务错误类问题 business
 * <p>D-数据库错误
 * <p>F-文件系统错误
 * <p>N-网络通信类错误 network
 * <p>P-权限类错误 permission
 * <p>O-外部系统错误
 * <p>S-系统类错误 system
 * 错误码顺位号:
 * 各系统自增
 *
 * @author moon on 2023/6/27
 */
public interface ServiceErrorInfo {

    // 交易成功: 000000
    // 模块 dispatch 错误码区间 [E-001-000-V-000 ~ E-001-000-S-000)

    String getCode();

    String getMessage();


}
