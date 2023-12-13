package pers.sea.shield.dispatch.common.util;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

/**
 * @author moon on 12/13/2023
 */
@Slf4j
public class XMLUtil {

    /**
     * 特殊集合标签
     */
    private static final List<String> SPECIAL_TAG = List.of("sdo");

    /**
     * 解析xml为json
     *
     * @param root dom4j#Element
     * @return json
     */
    public static ObjectNode XML2JSON(Element root) {
        ObjectNode rootNode = JsonNodeFactory.instance.objectNode();
        // 遍历节点
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            if (SPECIAL_TAG.contains(element.getName())) {
                ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
                arrayNode.add(XML2JSON(element));
                rootNode.set(element.getName(), arrayNode);
            } else if (!element.elements().isEmpty()) {
                rootNode.set(element.getName(), XML2JSON(element));
            } else {
                rootNode.put(element.getName(), element.getTextTrim());
            }
        }

        return rootNode;
    }

    /**
     * dom4j 解析 xml string
     *
     * @param xml xml string
     * @return dom4j#Document
     */
    public static Document getDocument(String xml) {
        try {
            return DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            log.error("XML2JSON function failed", e);
            return null;
        }
    }
}
