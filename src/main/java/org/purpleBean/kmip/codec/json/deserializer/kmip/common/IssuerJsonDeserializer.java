package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Issuer;

import java.io.IOException;

public class IssuerJsonDeserializer extends KmipDataTypeJsonDeserializer<Issuer> {
    private final KmipTag kmipTag = Issuer.kmipTag;
    private final EncodingType encodingType = Issuer.encodingType;

    @Override
    public Issuer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Issuer.class, String.format("JSON node cannot be null for Issuer deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Issuer.class, String.format("Invalid KMIP tag for Issuer"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Issuer.class, String.format("Failed to parse KMIP tag for Issuer: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Issuer.class,
                    String.format("Expected object with %s tag for Issuer, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Issuer.class, String.format("Missing or non-text 'type' field for Issuer"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Issuer.class, "Issuer 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        Issuer issuer = Issuer.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!issuer.isSupported()) {
            ctxt.reportInputMismatch(Issuer.class, "Issuer not supported for spec " + spec);
            return null;
        }

        return issuer;
    }
}