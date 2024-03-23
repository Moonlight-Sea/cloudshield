package pers.sea.shield.dispatch.api.impl;

import static pers.sea.shield.dispatch.common.constant.DispatchCommonConstant.NODE_BODY;
import static pers.sea.shield.dispatch.common.constant.DispatchCommonConstant.NODE_ITEM_CODE;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pers.sea.shield.common.core.exception.CloudShieldException;
import pers.sea.shield.common.core.util.AviatorScriptUtil;
import pers.sea.shield.dispatch.api.IDispatchService;
import pers.sea.shield.dispatch.common.enums.DispatchErrorInfo;
import pers.sea.shield.dispatch.common.enums.ParamInfoMustInputCheckEnum;
import pers.sea.shield.dispatch.common.util.JsonNodeUtil;
import pers.sea.shield.dispatch.pojo.entity.ApiInfo;
import pers.sea.shield.dispatch.service.IApiInfoService;
import pers.sea.shield.dispatch.service.IParamInfoService;

/**
 * dispatch 业务服务实现
 *
 * @author moon on 12/13/2023
 */
@Service
public class DispatchServiceImpl implements IDispatchService {

    private final IApiInfoService apiInfoService;
    private final IParamInfoService paramInfoService;
    private final RestTemplate restTemplate;
    // 映射表，将HTTP方法映射到对应的策略
    private final Map<String, HttpRequestStrategy> httpMethodStrategies = new HashMap<>();

    public DispatchServiceImpl(IApiInfoService apiInfoService,
            IParamInfoService paramInfoService,
            RestTemplate restTemplate) {
        this.apiInfoService = apiInfoService;
        this.paramInfoService = paramInfoService;
        this.restTemplate = restTemplate;
        init();
    }

    private void init() {
        // 可以很容易地添加对其他HTTP方法的支持
        httpMethodStrategies.put("GET",
                (apiUrl, param) -> restTemplate.getForObject(apiUrl, ObjectNode.class, param));
        httpMethodStrategies.put("POST",
                (apiUrl, param) -> restTemplate.postForObject(apiUrl, param, ObjectNode.class));
    }


    @Override
    public String dispatch(ObjectNode node) throws CloudShieldException {

        // 1. 根据node中的`BODY`->`ItemCd`字段，调用相应的业务方法
        String itemCode = JsonNodeUtil.getNodeValue(node, NODE_BODY, NODE_ITEM_CODE);
        if (StringUtils.isBlank(itemCode)) {
            throw new CloudShieldException(DispatchErrorInfo.PARAM_NECESSARY_MISSING,
                    DispatchErrorInfo.PARAM_NECESSARY_MISSING.getMessage() + ": BODY.ItemCd");
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

    // 私有方法
    // ---------------------------------------------------------------------------------------------

    /**
     * 必输参数校验 failed throw CloudShieldException
     *
     * @param uuid apiInfoUUId
     * @param node JsonNode
     */
    private void checkParam(String uuid, ObjectNode node) {
        paramInfoService.listByApiUuid(uuid).stream()
                .filter(paramInfo -> paramInfo.getMustInputCheck()
                        == ParamInfoMustInputCheckEnum.MUST_INPUT)
                .forEach(paramInfo -> {
                    String paramName = paramInfo.getCode();
                    String paramValue = JsonNodeUtil.getNodeValue(node, NODE_BODY, paramName);
                    if (StringUtils.isBlank(paramValue)) {
                        String message = String.format("%s: BODY.%s",
                                DispatchErrorInfo.PARAM_NECESSARY_MISSING.getMessage(), paramName);
                        throw new CloudShieldException(DispatchErrorInfo.PARAM_NECESSARY_MISSING,
                                message);
                    }
                });
    }

    /**
     * 组装请求参数
     * <p>if apiTemplate is null, return null</p>
     *
     * @param apiInfo apiInfo
     * @param node    ObjectNode
     * @return aviatorScript assembled param
     */
    private String assembleParam(ApiInfo apiInfo, ObjectNode node) {
        if (StringUtils.isBlank(apiInfo.getApiTemplate())) {
            return null;
        }
        return AviatorScriptUtil.executeWithCache(apiInfo.getApiTemplate(), node).toString();
    }

    private ObjectNode remoteCall(String apiMethod, String apiUrl, String param)
            throws CloudShieldException {
        try {
            return httpMethodStrategies.get(apiMethod).execute(apiUrl, param);
        } catch (Exception e) {
            // 改进了异常处理，添加了更多的错误上下文
            String errorMsg =
                    "Error during remote call using method: " + apiMethod + ", URL: " + apiUrl;
            if (e instanceof CloudShieldException) {
                throw (CloudShieldException) e;
            } else {
                throw new CloudShieldException(DispatchErrorInfo.REMOTE_REQUEST_ERROR,
                        errorMsg + ", cause: " + e.getMessage(), e);
            }
        }
    }


    private String assembleResult(String resultTemplate, ObjectNode result) {
        return AviatorScriptUtil.executeWithCache(resultTemplate, result).toString();
    }

    private void pushResult(String resultUrl, String assembleResult) {
        if (StringUtils.isNotBlank(resultUrl)) {
            restTemplate.postForObject(resultUrl, assembleResult, ObjectNode.class);
        }
    }


    // 策略模式的执行策略类
    private interface HttpRequestStrategy {

        ObjectNode execute(String apiUrl, String param) throws Exception;
    }

}
