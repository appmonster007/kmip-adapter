package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.NameValue;

import java.io.IOException;
import java.util.NoSuchElementException;

public class NameValueJsonDeserializer extends KmipDataTypeJsonDeserializer<NameValue> {
    private final KmipTag kmipTag = NameValue.kmipTag;
    private final EncodingType encodingType = NameValue.encodingType;

    @Override
    public NameValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(NameValue.class, String.format("JSON node cannot be null for NameValue deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(NameValue.class, String.format("Invalid KMIP tag for NameValue"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(NameValue.class, String.format("Failed to parse KMIP tag for NameValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(NameValue.class,
                    String.format("Expected object with %s tag for NameValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(NameValue.class, String.format("Missing or non-text 'type' field for NameValue"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NameValue.class, "NameValue 'value' must be a non-empty array");
            return null;
        }

        NameValue nameValue = NameValue.builder().value(valueNode.asText()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!nameValue.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("NameValue is not supported for KMIP spec %s", spec));
        }

        return nameValue;
    }
}
