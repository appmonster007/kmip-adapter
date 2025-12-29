package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Username;

import java.io.IOException;

public class UsernameJsonDeserializer extends KmipDataTypeJsonDeserializer<Username> {
    private final KmipTag kmipTag = Username.kmipTag;
    private final EncodingType encodingType = Username.encodingType;

    @Override
    public Username deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Username.class, String.format("JSON node cannot be null for Username deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Username.class, String.format("Invalid KMIP tag for Username"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Username.class, String.format("Failed to parse KMIP tag for Username: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Username.class,
                    String.format("Expected object with %s tag for Username, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Username.class, String.format("Missing or non-text 'type' field for Username"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Username.class, "Username 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        Username username = Username.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!username.isSupported()) {
            ctxt.reportInputMismatch(Username.class, "Username not supported for spec " + spec);
            return null;
        }

        return username;
    }
}