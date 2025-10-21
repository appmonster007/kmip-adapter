package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CertificateLength;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class CertificateLengthJsonDeserializer extends KmipDataTypeJsonDeserializer<CertificateLength> {
    private final KmipTag kmipTag = CertificateLength.kmipTag;
    private final EncodingType encodingType = CertificateLength.encodingType;

    @Override
    public CertificateLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CertificateLength.class, String.format("JSON node cannot be null for CertificateLength deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CertificateLength.class, String.format("Invalid KMIP tag for CertificateLength"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CertificateLength.class, String.format("Failed to parse KMIP tag for CertificateLength: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CertificateLength.class,
                    String.format("Expected object with %s tag for CertificateLength, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CertificateLength.class, String.format("Missing or non-text 'type' field for CertificateLength"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(CertificateLength.class, "CertificateLength 'value' must be a non-empty number");
            return null;
        }

        Integer value = valueNode.asInt();
        CertificateLength certificateLength = CertificateLength.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!certificateLength.isSupported()) {
            ctxt.reportInputMismatch(CertificateLength.class, "CertificateLength not supported for spec " + spec);
            return null;
        }

        return certificateLength;
    }
}
