package pers.sea.shield.dispatch.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * jackson jsonNode Util
 *
 * @author moon on 12/13/2023
 */
public class JsonNodeUtil {

    public static String getNodeValue(ObjectNode node, String... paths) {
        if (node == null) {
            return null;
        }
        JsonNode nodeItem = node;

        int i = 0;
        while (i < paths.length) {
            if (i == paths.length - 1) {
                return nodeItem.get(paths[i]).asText();
            }
            if (nodeItem.get(paths[i]) == null) {
                return null;
            }
            nodeItem = nodeItem.get(paths[i]);

            i++;
        }
        return null;
    }

}
