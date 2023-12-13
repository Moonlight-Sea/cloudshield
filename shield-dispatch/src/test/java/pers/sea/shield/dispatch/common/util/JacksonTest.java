package pers.sea.shield.dispatch.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author moon on 12/12/2023
 */
class JacksonTest {


    String xml;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        xml = FileUtil.readString(ResourceUtil.getResource("a.xml"), UTF_8);
    }


    @org.junit.jupiter.api.Test
    void test() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

        JsonNode jsonNode = xmlMapper.readTree(xml);
        System.out.println(jsonNode.toPrettyString());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}