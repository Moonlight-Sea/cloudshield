package pers.sea.shield.dispatch.controller.external;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.dom4j.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.sea.shield.common.core.exception.CloudShieldException;
import pers.sea.shield.dispatch.api.IDispatchService;
import pers.sea.shield.dispatch.common.enums.DispatchErrorInfo;
import pers.sea.shield.dispatch.common.util.XMLUtil;

/**
 * 快速派遣服务
 *
 * @author moon on 6/13/2023
 */
@RestController
public class DispatchController {

    private final IDispatchService dispatchService;

    public DispatchController(IDispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }


    /**
     * 处理 esb xml报文
     *
     * <pre>
     *     // esb xml报文
     *     eg: <service>
     *          <SYS_HEAD>
     *              xxx
     *          </SYS_HEAD>
     *          <APP_HEAD>
     *              xxx
     *          </APP_HEAD>
     *          <BODY>
     *              <ItemCd>XXXXXX</ItemCd>
     *              xxx
     *          </BODY>
     *         </service>
     * </pre>
     *
     * @param request ESB => XML报文信息
     * @return XML报文 => ESB
     */
    @PostMapping(value = "/api", produces = {MediaType.APPLICATION_XML_VALUE})
    public String entrance(@RequestBody String request) {
        int count = Integer.parseInt(request.substring(0, request.indexOf("<?xml")));
        String substring = request.substring(request.indexOf("<?xml"));
        if (count != substring.length()) {
            // xml的文件内容和xml的长度不一致, 内容被修改
            throw new CloudShieldException(DispatchErrorInfo.PARAM_XML_INVALID);
        }
        Document doc = XMLUtil.getDocument(substring);
        if (doc == null) {
            throw new CloudShieldException(DispatchErrorInfo.PARAM_XML_INVALID);
        }
        ObjectNode jsonNode = XMLUtil.XML2JSON(doc.getRootElement());
        if (jsonNode == null) {
            throw new CloudShieldException(DispatchErrorInfo.PARAM_INVALID);
        }
        return dispatchService.dispatch(jsonNode);
    }

}
