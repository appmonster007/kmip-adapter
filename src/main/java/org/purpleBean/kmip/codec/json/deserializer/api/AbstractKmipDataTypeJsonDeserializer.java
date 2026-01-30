package org.purpleBean.kmip.codec.json.deserializer.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.*;

import java.io.IOException;
import java.util.NoSuchElementException;

public abstract class AbstractKmipDataTypeJsonDeserializer<T extends KmipDataType, B> extends KmipDataTypeJsonDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;

    protected AbstractKmipDataTypeJsonDeserializer(KmipTag kmipTag, EncodingType encodingType) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        B builder = createBuilder();

        JsonNode node = ctxt.readTree(p);

        if (node == null) {
            ctxt.reportInputMismatch(handledType(), "JSON node cannot be null");
            return null;
        }

        String tag = getTag(node, ctxt, builder);
        if (tag == null) {
            return null;
        }

        String type = getType(node, ctxt, builder);
        if (type == null) {
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(handledType(), "Missing 'value' field");
            return null;
        }

        if (EncodingType.STRUCTURE.getDescription().equals(type)) {
            if (!valueNode.isArray()) {
                ctxt.reportInputMismatch(handledType(), "Missing or invalid 'value' array for " + handledType().getSimpleName());
                return null;
            }

            for (JsonNode value : valueNode) {
                if (!value.has("tag")) {
                    continue;
                }
                if (!value.has("type")) {
                    continue;
                }
                JsonParser valueParser = value.traverse(p.getCodec());
                setValue(builder, value.get("tag").asText(), type, valueParser, ctxt);
            }
        } else {
            JsonParser valueParser = valueNode.traverse(p.getCodec());
            valueParser.nextToken();
            setValue(builder, null, type, valueParser, ctxt);
        }

        T result = build(builder);

        verifyVersionSupport(result);
        return result;
    }

    protected void verifyVersionSupport(T result) {
        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupported()) {
            throw new NoSuchElementException(String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
        }
    }

    protected String getTag(JsonNode node, DeserializationContext ctxt, B builder) throws IOException {
        JsonNode nameNode = node.get("name");
        JsonNode tagNode = node.get("tag");

        String value = null;
        if (nameNode != null && nameNode.isTextual()) {
            value = nameNode.asText();
        } else if (tagNode != null && tagNode.isTextual()) {
            value = tagNode.asText();
        }

        if (value == null) {
            ctxt.reportInputMismatch(KmipTag.class,
                    "Expected 'name' or 'tag' field with string value in object");
            return null;
        }

        if (!value.equals(kmipTag.getDescription())) {
            ctxt.reportInputMismatch(handledType(), "Expected object with tag " + kmipTag.getValue());
            return null;
        }
        return value;
    }

    protected String getType(JsonNode node, DeserializationContext ctxt, B builder) throws IOException {
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || !encodingType.getDescription().equals(typeNode.asText())
        ) {
            ctxt.reportInputMismatch(handledType(), "Missing or invalid 'type' field");
            return null;
        }
        return typeNode.asText();
    }

    protected abstract B createBuilder();

    protected abstract void setValue(B builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException;

    protected abstract T build(B builder);

}