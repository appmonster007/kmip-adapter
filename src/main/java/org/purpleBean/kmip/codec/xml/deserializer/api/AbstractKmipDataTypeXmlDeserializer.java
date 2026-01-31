package org.purpleBean.kmip.codec.xml.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.api.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractKmipDataTypeXmlDeserializer<T extends KmipDataType, B> extends KmipDataTypeXmlDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;

    protected AbstractKmipDataTypeXmlDeserializer(KmipTag kmipTag, EncodingType encodingType) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        B builder = createBuilder();

        if (p.currentToken() == null) {
            p.nextToken();
        }

        String xmlTagName = null;
        if (p instanceof FromXmlParser xmlParser) {
            xmlTagName = xmlParser.getStaxReader().getLocalName();
        } else {
            xmlTagName = (String) ctxt.getAttribute("tag");
        }

        JsonNode node = ctxt.readTree(p);

        if (node == null) {
            ctxt.reportInputMismatch(handledType(), "XML node cannot be null");
            return null;
        }

        String tag = getTag(xmlTagName, node, ctxt, builder);
        if (tag == null) {
            return null;
        }

        String type = getType(node, ctxt, builder);
        if (type == null) {
            return null;
        }

        if (EncodingType.STRUCTURE.getDescription().equals(type)) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String fieldName = field.getKey();
                JsonNode fieldValue = field.getValue();

                if ("type".equals(fieldName) || "tag".equals(fieldName)) {
                    continue;
                }

                if (fieldValue.isArray()) {
                    for (JsonNode item : fieldValue) {
                        processChild(builder, fieldName, type, item, p, ctxt);
                    }
                } else {
                    processChild(builder, fieldName, type, fieldValue, p, ctxt);
                }
            }
        } else {
            JsonNode valueNode = node.get("value");
            if (valueNode == null) {
                // Check for text content which Jackson XML maps to empty string key
                valueNode = node.get("");
            }

            if (valueNode == null) {
                ctxt.reportInputMismatch(handledType(), "Missing 'value' field");
                return null;
            }
            JsonParser valueParser = valueNode.traverse(p.getCodec());
            valueParser.nextToken();
            setValue(builder, tag, type, valueParser, ctxt);
        }

        T result = build(builder);

        verifyVersionSupport(result);
        return result;
    }

    private void processChild(B builder, String fieldName, String parentType, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        String itemTag = fieldName;
        if ("TTLV".equals(itemTag) && node.has("tag")) {
            itemTag = node.get("tag").asText();
        }
        ctxt.setAttribute("tag", itemTag);
        JsonParser childParser = node.traverse(p.getCodec());
        childParser.nextToken();
        setValue(builder, itemTag, parentType, childParser, ctxt);
    }

    protected void verifyVersionSupport(T result) {
        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            throw new NoSuchElementException(String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
        }
    }

    protected String getTag(String xmlTagName, JsonNode node, DeserializationContext ctxt, B builder) throws IOException {
        String tag = xmlTagName;
        if ("TTLV".equals(tag) && node.has("tag")) {
            tag = node.get("tag").asText();
            return tag;
        }

        if (kmipTag.getDescription().equals(tag)) {
            return tag;
        }

        ctxt.reportInputMismatch(handledType(), "Expected object with tag " + kmipTag.getDescription());
        return null;
    }

    protected String getType(JsonNode node, DeserializationContext ctxt, B builder) throws IOException {
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual()) {
            if (encodingType == EncodingType.STRUCTURE) {
                return EncodingType.STRUCTURE.getDescription();
            }
            ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
            return null;
        }

        String type = typeNode.asText();
        if (!encodingType.getDescription().equals(type)) {
            ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
            return null;
        }
        return type;
    }

    protected abstract B createBuilder();

    protected abstract void setValue(B builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException;

    protected abstract T build(B builder);

}