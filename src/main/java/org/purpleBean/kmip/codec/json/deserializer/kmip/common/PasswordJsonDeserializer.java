package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Password;

import java.io.IOException;

public class PasswordJsonDeserializer extends KmipDataTypeJsonDeserializer<Password> {
    private final KmipTag kmipTag = Password.kmipTag;
    private final EncodingType encodingType = Password.encodingType;

    @Override
    public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Password.class, String.format("JSON node cannot be null for Password deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Password.class, String.format("Invalid KMIP tag for Password"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Password.class, String.format("Failed to parse KMIP tag for Password: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Password.class,
                    String.format("Expected object with %s tag for Password, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Password.class, String.format("Missing or non-text 'type' field for Password"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Password.class, "Password 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        Password password = Password.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!password.isSupported()) {
            ctxt.reportInputMismatch(Password.class, "Password not supported for spec " + spec);
            return null;
        }

        return password;
    }
}