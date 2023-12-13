package pers.sea.shield.dispatch.controller.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快速派遣服务
 *
 * @author moon on 6/13/2023
 */
@RestController
public class DispatchController {

    /**
     * 处理xml报文
     *
     * @param request ESB => XML报文信息
     * @return XML报文 => ESB
     */
    @PostMapping(value = "/api", produces = {MediaType.APPLICATION_XML_VALUE})
    public String entrance(@RequestBody String request) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(request);
        return jsonNode.toPrettyString();
    }

}
