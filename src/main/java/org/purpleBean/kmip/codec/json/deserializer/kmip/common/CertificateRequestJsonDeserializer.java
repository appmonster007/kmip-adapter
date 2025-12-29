package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CertificateRequestJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateRequest> {
    private final KmipTag kmipTag = CertificateRequest.kmipTag;
    private final EncodingType encodingType = CertificateRequest.encodingType;

    @Override
    public CertificateRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CertificateRequest.class, String.format("JSON node cannot be null for CertificateRequest deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateRequest.class, String.format("Invalid KMIP tag for CertificateRequest"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateRequest.class, String.format("Failed to parse KMIP tag for CertificateRequest: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateRequest.class,
                    String.format("Expected object with %s tag for CertificateRequest, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateRequest.class, String.format("Missing or non-text 'type' field for CertificateRequest"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CertificateRequest.class, "CertificateRequest 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        CertificateRequest certificateRequest = CertificateRequest.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!certificateRequest.isSupported()) {
            ctxt.reportInputMismatch(CertificateRequest.class, "CertificateRequest not supported for spec " + spec);
            return null;
        }

        return certificateRequest;
    }
}