package pers.sea.shield.common.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.Map;

/**
 * aviator工具类
 *
 * @author moon on 12/6/2023
 */
public class AviatorScriptUtil {



    public static Object executeWithCache(String script, JsonNode args) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> env = mapper.convertValue(args, new TypeReference<>() {
        });
        // 使用编译缓存模式, 第一次编译缓存后，无论脚本是否改变，都会使用缓存
        return AviatorEvaluator.execute(script, env, true);
    }
}


