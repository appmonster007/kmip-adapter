package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;

import java.io.IOException;

public class RevocationMessageJsonDeserializer extends KmipDataTypeJsonDeserializer<RevocationMessage> {
    private final KmipTag kmipTag = RevocationMessage.kmipTag;
    private final EncodingType encodingType = RevocationMessage.encodingType;

    @Override
    public RevocationMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(RevocationMessage.class, String.format("JSON node cannot be null for RevocationMessage deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RevocationMessage.class, String.format("Invalid KMIP tag for RevocationMessage"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RevocationMessage.class, String.format("Failed to parse KMIP tag for RevocationMessage: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(RevocationMessage.class,
                    String.format("Expected object with %s tag for RevocationMessage, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RevocationMessage.class, String.format("Missing or non-text 'type' field for RevocationMessage"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RevocationMessage.class, "RevocationMessage 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        RevocationMessage revocationMessage = RevocationMessage.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!revocationMessage.isSupported()) {
            ctxt.reportInputMismatch(RevocationMessage.class, "RevocationMessage not supported for spec " + spec);
            return null;
        }

        return revocationMessage;
    }
}