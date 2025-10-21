package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CryptographicUsageMask;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class CryptographicUsageMaskJsonDeserializer extends KmipDataTypeJsonDeserializer<CryptographicUsageMask> {
    private final KmipTag kmipTag = CryptographicUsageMask.kmipTag;
    private final EncodingType encodingType = CryptographicUsageMask.encodingType;

    @Override
    public CryptographicUsageMask deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, String.format("JSON node cannot be null for CryptographicUsageMask deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CryptographicUsageMask.class, String.format("Invalid KMIP tag for CryptographicUsageMask"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, String.format("Failed to parse KMIP tag for CryptographicUsageMask: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class,
                    String.format("Expected object with %s tag for CryptographicUsageMask, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, String.format("Missing or non-text 'type' field for CryptographicUsageMask"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "CryptographicUsageMask 'value' must be a non-empty array");
            return null;
        }

        CryptographicUsageMask cryptographicUsageMask = CryptographicUsageMask.builder().value(valueNode.intValue()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!cryptographicUsageMask.isSupported()) {
            ctxt.reportInputMismatch(CryptographicUsageMask.class, "CryptographicUsageMask not supported for spec " + spec);
            return null;
        }

        return cryptographicUsageMask;
    }
}
