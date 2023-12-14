package pers.sea.shield.dispatch.api.impl;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pers.sea.shield.common.core.exception.CloudShieldException;
import pers.sea.shield.common.core.util.AviatorScriptUtil;
import pers.sea.shield.dispatch.api.IDispatchService;
import pers.sea.shield.dispatch.common.enums.DispatchErrorInfo;
import pers.sea.shield.dispatch.common.enums.ParamInfoMustInputCheckEnum;
import pers.sea.shield.dispatch.common.util.JsonNodeUtil;
import pers.sea.shield.dispatch.pojo.entity.ApiInfo;
import pers.sea.shield.dispatch.service.IApiInfoService;
import pers.sea.shield.dispatch.service.IParamInfoService;

import static pers.sea.shield.dispatch.common.constant.DispatchCommonConstant.NODE_BODY;
import static pers.sea.shield.dispatch.common.constant.DispatchCommonConstant.NODE_ITEM_CODE;

/**
 * dispatch 业务服务实现
 *
 * @author moon on 12/13/2023
 */
@Service
public class DispatchServiceImpl implements IDispatchService {

    private final IApiInfoService apiInfoService;
    private final IParamInfoService paramInfoService;
    private final ObjectMapper objectMapper;

    public DispatchServiceImpl(IApiInfoService apiInfoService,
                               IParamInfoService paramInfoService,
                               ObjectMapper objectMapper) {
        this.apiInfoService = apiInfoService;
        this.paramInfoService = paramInfoService;
        this.objectMapper = objectMapper;
    }

    @Override
    public String dispatch(ObjectNode node) throws CloudShieldException {

        // 1. 根据node中的`BODY`->`ItemCd`字段，调用相应的业务方法
        String itemCode = JsonNodeUtil.getNodeValue(node, NODE_BODY, NODE_ITEM_CODE);
        if (StringUtils.isBlank(itemCode)) {
            throw new CloudShieldException(DispatchErrorInfo.PARAM_NECESSARY_MISSING, DispatchErrorInfo.PARAM_NECESSARY_MISSING.getMessage() + ": BODY.ItemCd");
        }
        ApiInfo apiInfo = apiInfoService.getApiInfoByItemCode(itemCode);
        if (apiInfo == null) {
            throw new CloudShieldException(DispatchErrorInfo.API_INFO_NOT_FOUND);
        }


        // 1.1 必输参数校验 failed throw CloudShieldException
        checkParam(apiInfo.getUuid(), node);


        // 2. 根据业务配置，组装流程引擎的请求参数，并调用流程引擎接口
        // 2.1 组装请求参数
        String param = assembleParam(apiInfo, node);
        // 2.2 调用流程引擎接口
        ObjectNode result = remoteCall(apiInfo.getApiMethod(), apiInfo.getApiUrl(), param);


        // 3. 根据流程引擎返回的结果，组装返回结果
        String assembleResult = assembleResult(apiInfo.getResultTemplate(), result);

        // (optional) 4. 推送到其他系统
        pushResult(apiInfo.getResultUrl(), assembleResult);


        return assembleResult;
    }


    private void checkParam(String uuid, ObjectNode node) {
        paramInfoService.listByApiUuid(uuid).stream()
                .filter(paramInfo -> paramInfo.getMustInputCheck() == ParamInfoMustInputCheckEnum.MUST_INPUT)
                .forEach(paramInfo -> {
                    String paramName = paramInfo.getCode();
                    String paramValue = JsonNodeUtil.getNodeValue(node, NODE_BODY, paramName);
                    if (StringUtils.isBlank(paramValue)) {
                        String message = String.format("%s: BODY.%s", DispatchErrorInfo.PARAM_NECESSARY_MISSING.getMessage(), paramName);
                        throw new CloudShieldException(DispatchErrorInfo.PARAM_NECESSARY_MISSING, message);
                    }
                });
    }


    private String assembleParam(ApiInfo apiInfo, ObjectNode node) {
        if (StringUtils.isBlank(apiInfo.getApiTemplate())) {
            return null;
        }
        return AviatorScriptUtil.executeWithCache(apiInfo.getApiTemplate(), node).toString();
    }


    private ObjectNode remoteCall(String apiMethod, String apiUrl, String param) throws CloudShieldException {
        String result = switch (apiMethod) {
            case "GET" -> HttpUtil.get(apiUrl);
            case "POST" -> HttpUtil.post(apiUrl, param);
            default ->
                    throw new CloudShieldException(DispatchErrorInfo.REMOTE_REQUEST_NOT_SUPPORT_METHOD, DispatchErrorInfo.REMOTE_REQUEST_NOT_SUPPORT_METHOD.getMessage() + ": " + apiMethod);
        };
        try {
            return (ObjectNode) objectMapper.readTree(result);
        } catch (JsonProcessingException e) {
            throw new CloudShieldException(DispatchErrorInfo.REMOTE_REQUEST_ERROR, e.getMessage(), e);
        }
    }


    private String assembleResult(String resultTemplate, ObjectNode result) {
        return AviatorScriptUtil.executeWithCache(resultTemplate, result).toString();
    }


    private void pushResult(String resultUrl, String assembleResult) {
        if (StringUtils.isNotBlank(resultUrl)) {
            HttpUtil.post(resultUrl, assembleResult);
        }
    }
}
