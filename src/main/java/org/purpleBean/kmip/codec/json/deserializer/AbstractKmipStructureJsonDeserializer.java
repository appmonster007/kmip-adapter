package org.purpleBean.kmip.codec.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;

import java.io.IOException;

public abstract class AbstractKmipStructureJsonDeserializer<T extends KmipStructure, B> extends KmipDataTypeJsonDeserializer<T> {

    private final KmipTag kmipTag;
    private final EncodingType encodingType;

    protected AbstractKmipStructureJsonDeserializer(KmipTag kmipTag, EncodingType encodingType) {
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = ctxt.readTree(p);

        if (node == null) {
            ctxt.reportInputMismatch(handledType(), "JSON node cannot be null");
            return null;
        }

        KmipTag tag;
        try {
            tag = ctxt.readTreeAsValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(handledType(), "Invalid KMIP tag");
                return null;
            }
        } catch (Exception e) {
            ctxt.handleWeirdStringValue(handledType(), node.toString(), "Failed to parse KMIP tag: " + e.getMessage());
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(handledType(), "Expected object with tag " + kmipTag.getValue().getValue());
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || !encodingType.getDescription().equals(typeNode.asText())
        ) {
            ctxt.reportInputMismatch(handledType(), "Missing or non-text 'type' field for " + handledType().getSimpleName());
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray()) {
            ctxt.reportInputMismatch(handledType(), "Missing or invalid 'value' array for " + handledType().getSimpleName());
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        B builder = createBuilder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) {
                continue;
            }
            KmipTag.Value nodeTag = ctxt.readTreeAsValue(valueNode, KmipTag.class).getValue();
            setValue(builder, nodeTag, valueNode.traverse(p.getCodec()), ctxt);
        }

        T result = build(builder);

        if (!result.isSupported()) {
            ctxt.reportInputMismatch(handledType(), String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
            return null;
        }

        return result;
    }

    protected abstract B createBuilder();

    protected abstract void setValue(B builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException;

    protected abstract T build(B builder);
}