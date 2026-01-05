package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AlternativeNameValue;

import java.io.IOException;

public class AlternativeNameValueJsonDeserializer extends KmipDataTypeJsonDeserializer<AlternativeNameValue> {
    private final KmipTag kmipTag = AlternativeNameValue.kmipTag;
    private final EncodingType encodingType = AlternativeNameValue.encodingType;

    @Override
    public AlternativeNameValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, String.format("JSON node cannot be null for AlternativeNameValue deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AlternativeNameValue.class, String.format("Invalid KMIP tag for AlternativeNameValue"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, String.format("Failed to parse KMIP tag for AlternativeNameValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AlternativeNameValue.class,
                    String.format("Expected object with %s tag for AlternativeNameValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, String.format("Missing or non-text 'type' field for AlternativeNameValue"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, "AlternativeNameValue 'value' must be a non-empty array");
            return null;
        }

        AlternativeNameValue alternativeNameValue = AlternativeNameValue.builder().value(valueNode.asText()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!alternativeNameValue.isSupported()) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, "AlternativeNameValue not supported for spec " + spec);
            return null;
        }

        return alternativeNameValue;
    }
}