package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.io.IOException;
import java.math.BigInteger;

public class PrivateExponentJsonDeserializer extends KmipDataTypeJsonDeserializer<PrivateExponent> {
    private final KmipTag kmipTag = PrivateExponent.kmipTag;
    private final EncodingType encodingType = PrivateExponent.encodingType;

    @Override
    public PrivateExponent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(PrivateExponent.class, String.format("JSON node cannot be null for PrivateExponent deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PrivateExponent.class, String.format("Invalid KMIP tag for PrivateExponent"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PrivateExponent.class, String.format("Failed to parse KMIP tag for PrivateExponent: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PrivateExponent.class,
                    String.format("Expected object with %s tag for PrivateExponent, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PrivateExponent.class, String.format("Missing or non-text 'type' field for PrivateExponent"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrivateExponent.class, "PrivateExponent 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        PrivateExponent privateExponent = PrivateExponent.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!privateExponent.isSupported()) {
            ctxt.reportInputMismatch(PrivateExponent.class, "PrivateExponent not supported for spec " + spec);
            return null;
        }

        return privateExponent;
    }
}