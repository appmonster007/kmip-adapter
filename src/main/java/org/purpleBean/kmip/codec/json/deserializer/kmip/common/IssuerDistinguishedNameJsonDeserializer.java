package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IssuerDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerDistinguishedNameJsonDeserializer extends KmipDataTypeJsonDeserializer<IssuerDistinguishedName> {
    private final KmipTag kmipTag = IssuerDistinguishedName.kmipTag;
    private final EncodingType encodingType = IssuerDistinguishedName.encodingType;

    @Override
    public IssuerDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, String.format("JSON node cannot be null for IssuerDistinguishedName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(IssuerDistinguishedName.class, String.format("Invalid KMIP tag for IssuerDistinguishedName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, String.format("Failed to parse KMIP tag for IssuerDistinguishedName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class,
                    String.format("Expected object with %s tag for IssuerDistinguishedName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, String.format("Missing or non-text 'type' field for IssuerDistinguishedName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "IssuerDistinguishedName 'value' must be present");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        IssuerDistinguishedName issuerDistinguishedName = IssuerDistinguishedName.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!issuerDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(IssuerDistinguishedName.class, "IssuerDistinguishedName not supported for spec " + spec);
            return null;
        }

        return issuerDistinguishedName;
    }
}
