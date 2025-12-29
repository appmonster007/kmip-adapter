package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.io.IOException;
import java.math.BigInteger;

public class PrimeExponentQJsonDeserializer extends KmipDataTypeJsonDeserializer<PrimeExponentQ> {
    private final KmipTag kmipTag = PrimeExponentQ.kmipTag;
    private final EncodingType encodingType = PrimeExponentQ.encodingType;

    @Override
    public PrimeExponentQ deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, String.format("JSON node cannot be null for PrimeExponentQ deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PrimeExponentQ.class, String.format("Invalid KMIP tag for PrimeExponentQ"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, String.format("Failed to parse KMIP tag for PrimeExponentQ: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PrimeExponentQ.class,
                    String.format("Expected object with %s tag for PrimeExponentQ, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, String.format("Missing or non-text 'type' field for PrimeExponentQ"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, "PrimeExponentQ 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        PrimeExponentQ primeExponentQ = PrimeExponentQ.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!primeExponentQ.isSupported()) {
            ctxt.reportInputMismatch(PrimeExponentQ.class, "PrimeExponentQ not supported for spec " + spec);
            return null;
        }

        return primeExponentQ;
    }
}