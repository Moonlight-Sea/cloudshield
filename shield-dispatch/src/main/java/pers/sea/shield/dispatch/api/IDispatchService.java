package pers.sea.shield.dispatch.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import pers.sea.shield.common.core.exception.CloudShieldException;

/**
 * dispatch 业务服务
 *
 * @author moon on 12/13/2023
 */
public interface IDispatchService {

    /**
     * dispatch 业务服务
     *
     * @param node 请求参数
     * @return 响应报文
     * @throws CloudShieldException 封装后的异常
     */
    String dispatch(ObjectNode node) throws CloudShieldException;
}
