package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.J;

import java.io.IOException;
import java.math.BigInteger;

public class JJsonDeserializer extends KmipDataTypeJsonDeserializer<J> {
    private final KmipTag kmipTag = J.kmipTag;
    private final EncodingType encodingType = J.encodingType;

    @Override
    public J deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(J.class, String.format("JSON node cannot be null for J deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(J.class, String.format("Invalid KMIP tag for J"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(J.class, String.format("Failed to parse KMIP tag for J: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(J.class,
                    String.format("Expected object with %s tag for J, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(J.class, String.format("Missing or non-text 'type' field for J"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(J.class, "J 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        J j = J.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!j.isSupported()) {
            ctxt.reportInputMismatch(J.class, "J not supported for spec " + spec);
            return null;
        }

        return j;
    }
}