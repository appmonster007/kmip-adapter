package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeFieldSizeJsonDeserializer extends KmipDataTypeJsonDeserializer<PrimeFieldSize> {
    private final KmipTag kmipTag = PrimeFieldSize.kmipTag;
    private final EncodingType encodingType = PrimeFieldSize.encodingType;

    @Override
    public PrimeFieldSize deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, String.format("JSON node cannot be null for PrimeFieldSize deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PrimeFieldSize.class, String.format("Invalid KMIP tag for PrimeFieldSize"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, String.format("Failed to parse KMIP tag for PrimeFieldSize: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class,
                    String.format("Expected object with %s tag for PrimeFieldSize, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, String.format("Missing or non-text 'type' field for PrimeFieldSize"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "PrimeFieldSize 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        PrimeFieldSize primeFieldSize = PrimeFieldSize.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!primeFieldSize.isSupported()) {
            ctxt.reportInputMismatch(PrimeFieldSize.class, "PrimeFieldSize not supported for spec " + spec);
            return null;
        }

        return primeFieldSize;
    }
}