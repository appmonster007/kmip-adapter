package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Fresh;

import java.io.IOException;

public class FreshJsonDeserializer extends KmipDataTypeJsonDeserializer<Fresh> {
    private final KmipTag kmipTag = Fresh.kmipTag;
    private final EncodingType encodingType = Fresh.encodingType;

    @Override
    public Fresh deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Fresh.class, String.format("JSON node cannot be null for Fresh deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Fresh.class, String.format("Invalid KMIP tag for Fresh"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Fresh.class, String.format("Failed to parse KMIP tag for Fresh: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Fresh.class,
                    String.format("Expected object with %s tag for Fresh, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Fresh.class, String.format("Missing or non-text 'type' field for Fresh"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isBoolean()) {
            ctxt.reportInputMismatch(Fresh.class, "Fresh 'value' must be a boolean");
            return null;
        }

        boolean value = valueNode.asBoolean();
        Fresh fresh = Fresh.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!fresh.isSupported()) {
            ctxt.reportInputMismatch(Fresh.class, "Fresh not supported for spec " + spec);
            return null;
        }

        return fresh;
    }
}