package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.P;

import java.io.IOException;
import java.math.BigInteger;

public class PJsonDeserializer extends KmipDataTypeJsonDeserializer<P> {
    private final KmipTag kmipTag = P.kmipTag;
    private final EncodingType encodingType = P.encodingType;

    @Override
    public P deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(P.class, String.format("JSON node cannot be null for P deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(P.class, String.format("Invalid KMIP tag for P"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(P.class, String.format("Failed to parse KMIP tag for P: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(P.class,
                    String.format("Expected object with %s tag for P, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(P.class, String.format("Missing or non-text 'type' field for P"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(P.class, "P 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        P result = P.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!result.isSupported()) {
            ctxt.reportInputMismatch(P.class, "P not supported for spec " + spec);
            return null;
        }

        return result;
    }
}