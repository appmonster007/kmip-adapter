package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateSerialNumber;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateSerialNumberJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateSerialNumber> {
    private final KmipTag kmipTag = CertificateSerialNumber.kmipTag;
    private final EncodingType encodingType = CertificateSerialNumber.encodingType;

    @Override
    public CertificateSerialNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, String.format("JSON node cannot be null for CertificateSerialNumber deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateSerialNumber.class, String.format("Invalid KMIP tag for CertificateSerialNumber"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, String.format("Failed to parse KMIP tag for CertificateSerialNumber: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class,
                    String.format("Expected object with %s tag for CertificateSerialNumber, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, String.format("Missing or non-text 'type' field for CertificateSerialNumber"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "CertificateSerialNumber 'value' must be present");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        CertificateSerialNumber certificateSerialNumber = CertificateSerialNumber.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!certificateSerialNumber.isSupported()) {
            ctxt.reportInputMismatch(CertificateSerialNumber.class, "CertificateSerialNumber not supported for spec " + spec);
            return null;
        }

        return certificateSerialNumber;
    }
}
