package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeName;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for AttributeName.
 */
public class AttributeNameJsonDeserializer extends KmipDataTypeJsonDeserializer<AttributeName> {
    private final KmipTag kmipTag = AttributeName.kmipTag;
    private final EncodingType encodingType = AttributeName.encodingType;

    @Override
    public AttributeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AttributeName.class, "JSON node cannot be null for AttributeName deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AttributeName.class, "Invalid KMIP tag for AttributeName");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AttributeName.class, String.format("Failed to parse KMIP tag for AttributeName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AttributeName.class, "Expected object for AttributeName");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AttributeName.class, "Missing or non-text 'type' field for AttributeName");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AttributeName.class, "Missing or non-text 'value' for AttributeName");
            return null;
        }

        String name = valueNode.asText();
        AttributeName attributeName = AttributeName.builder().value(name).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attributeName.isSupported()) {
            throw new NoSuchElementException(
                    String.format("AttributeName '%s' is not supported for KMIP spec %s", valueNode.asText(), spec)
            );
        }
        return attributeName;
    }
}
