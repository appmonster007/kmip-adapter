package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateValueJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateValue> {
    private final KmipTag kmipTag = CertificateValue.kmipTag;
    private final EncodingType encodingType = CertificateValue.encodingType;

    @Override
    public CertificateValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CertificateValue.class, String.format("JSON node cannot be null for CertificateValue deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateValue.class, String.format("Invalid KMIP tag for CertificateValue"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateValue.class, String.format("Failed to parse KMIP tag for CertificateValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateValue.class,
                    String.format("Expected object with %s tag for CertificateValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateValue.class, String.format("Missing or non-text 'type' field for CertificateValue"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateValue.class, "CertificateValue 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        CertificateValue certificateValue = CertificateValue.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!certificateValue.isSupported()) {
            ctxt.reportInputMismatch(CertificateValue.class, "CertificateValue not supported for spec " + spec);
            return null;
        }

        return certificateValue;
    }
}