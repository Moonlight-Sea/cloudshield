package pers.sea.shield.common.core.util;

import cn.hutool.core.io.FileUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author moon on 12/6/2023
 */
class AviatorScriptUtilTest {

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode args;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        try {
            args = objectMapper.readValue(FileUtil.file("./data/test.json"), JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @DisplayName("简单的加法计算")
    @org.junit.jupiter.api.Test
    void simplePlus() {
        Object result = AviatorScriptUtil.executeWithCache("1+1", null);
        assertEquals(2l, result);
    }

    @DisplayName("两个变量的加法计算")
    @org.junit.jupiter.api.Test
    void plusTwoArgs() {
        // a: 10; b: 12;
        Object result = AviatorScriptUtil.executeWithCache("#a+#b", args);
        assertEquals(22l, result);
    }

    @DisplayName("简单的表达式赋值")
    @org.junit.jupiter.api.Test
    void setPlaceholder() {
        Object result = AviatorScriptUtil.executeWithCache("'set placeholder value a: ' + #a", args);
        assertEquals("set placeholder value a: 1", result.toString());
    }
}