package pers.sea.shield.dispatch.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.dom4j.Document;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author moon on 12/12/2023
 */
class JacksonTest {


    String xml;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String xmlStr = FileUtil.readString(ResourceUtil.getResource("a.xml"), UTF_8);
        xml = xmlStr.substring(xmlStr.indexOf("<?xml"));
    }


    @org.junit.jupiter.api.Test
    void test() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        JsonNode jsonNode = xmlMapper.readTree(xml);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    void test1() {
        Document document = XMLUtil.getDocument(xml);
        assert document != null;
        JsonNode jsonNode = XMLUtil.XML2JSON(document.getRootElement());
        assert jsonNode != null;
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    void test2() {
        Document document = XMLUtil.getDocument(xml);
        assert document != null;
        ObjectNode jsonNode = XMLUtil.XML2JSON(document.getRootElement());
        String nodeItmeValue = JsonNodeUtil.getNodeValue(jsonNode, "BODY", "ItemCd");
        System.out.println(nodeItmeValue);
        Assert.equals(nodeItmeValue, "C000");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}