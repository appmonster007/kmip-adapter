package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IssuerAlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerAlternativeNameJsonDeserializer extends KmipDataTypeJsonDeserializer<IssuerAlternativeName> {
    private final KmipTag kmipTag = IssuerAlternativeName.kmipTag;
    private final EncodingType encodingType = IssuerAlternativeName.encodingType;

    @Override
    public IssuerAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class, String.format("JSON node cannot be null for IssuerAlternativeName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(IssuerAlternativeName.class, String.format("Invalid KMIP tag for IssuerAlternativeName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class, String.format("Failed to parse KMIP tag for IssuerAlternativeName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class,
                    String.format("Expected object with %s tag for IssuerAlternativeName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class, String.format("Missing or non-text 'type' field for IssuerAlternativeName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class, "IssuerAlternativeName 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        IssuerAlternativeName issuerAlternativeName = IssuerAlternativeName.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!issuerAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(IssuerAlternativeName.class, "IssuerAlternativeName not supported for spec " + spec);
            return null;
        }

        return issuerAlternativeName;
    }
}