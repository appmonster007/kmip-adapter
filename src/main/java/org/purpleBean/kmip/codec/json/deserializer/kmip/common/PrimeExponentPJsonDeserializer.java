package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeExponentPJsonDeserializer extends KmipDataTypeJsonDeserializer<PrimeExponentP> {
    private final KmipTag kmipTag = PrimeExponentP.kmipTag;
    private final EncodingType encodingType = PrimeExponentP.encodingType;

    @Override
    public PrimeExponentP deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(PrimeExponentP.class, String.format("JSON node cannot be null for PrimeExponentP deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PrimeExponentP.class, String.format("Invalid KMIP tag for PrimeExponentP"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PrimeExponentP.class, String.format("Failed to parse KMIP tag for PrimeExponentP: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PrimeExponentP.class,
                    String.format("Expected object with %s tag for PrimeExponentP, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PrimeExponentP.class, String.format("Missing or non-text 'type' field for PrimeExponentP"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrimeExponentP.class, "PrimeExponentP 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        PrimeExponentP primeExponentP = PrimeExponentP.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!primeExponentP.isSupported()) {
            ctxt.reportInputMismatch(PrimeExponentP.class, "PrimeExponentP not supported for spec " + spec);
            return null;
        }

        return primeExponentP;
    }
}