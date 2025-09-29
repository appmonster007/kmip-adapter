package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for RevocationReasonCode.
 */
public class RevocationReasonCodeJsonDeserializer extends KmipDataTypeJsonDeserializer<RevocationReasonCode> {
    private final KmipTag kmipTag = RevocationReasonCode.kmipTag;
    private final EncodingType encodingType = RevocationReasonCode.encodingType;

    @Override
    public RevocationReasonCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, "JSON node cannot be null for RevocationReasonCode deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RevocationReasonCode.class, "Invalid KMIP tag for RevocationReasonCode");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, String.format("Failed to parse KMIP tag for RevocationReasonCode: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(RevocationReasonCode.class,
                    String.format("Expected object with %s tag for RevocationReasonCode, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, "Missing or non-text 'type' field for RevocationReasonCode");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(RevocationReasonCode.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        RevocationReasonCode.Value revocationreasoncodeValue;
        try {
            revocationreasoncodeValue = RevocationReasonCode.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(RevocationReasonCode.class,
                    String.format("Unknown RevocationReasonCode value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        RevocationReasonCode revocationreasoncode = new RevocationReasonCode(revocationreasoncodeValue);

        // Final validation: Ensure constructed RevocationReasonCode is supported
        if (!revocationreasoncode.isSupported()) {
            throw new NoSuchElementException(
                    String.format("RevocationReasonCode '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return revocationreasoncode;
    }
}
