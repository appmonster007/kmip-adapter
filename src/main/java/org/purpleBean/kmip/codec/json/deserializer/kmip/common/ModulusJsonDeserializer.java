package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Modulus;

import java.io.IOException;
import java.math.BigInteger;

public class ModulusJsonDeserializer extends KmipDataTypeJsonDeserializer<Modulus> {
    private final KmipTag kmipTag = Modulus.kmipTag;
    private final EncodingType encodingType = Modulus.encodingType;

    @Override
    public Modulus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Modulus.class, String.format("JSON node cannot be null for Modulus deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Modulus.class, String.format("Invalid KMIP tag for Modulus"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Modulus.class, String.format("Failed to parse KMIP tag for Modulus: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Modulus.class,
                    String.format("Expected object with %s tag for Modulus, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Modulus.class, String.format("Missing or non-text 'type' field for Modulus"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Modulus.class, "Modulus 'value' must be a number");
            return null;
        }

        BigInteger value = p.getCodec().treeToValue(valueNode, BigInteger.class);
        Modulus modulus = Modulus.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!modulus.isSupported()) {
            ctxt.reportInputMismatch(Modulus.class, "Modulus not supported for spec " + spec);
            return null;
        }

        return modulus;
    }
}