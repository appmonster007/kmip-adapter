package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CriticalityIndicator;

import java.io.IOException;

public class CriticalityIndicatorJsonDeserializer extends KmipDataTypeJsonDeserializer<CriticalityIndicator> {
    private final KmipTag kmipTag = CriticalityIndicator.kmipTag;
    private final EncodingType encodingType = CriticalityIndicator.encodingType;

    @Override
    public CriticalityIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("JSON node cannot be null for CriticalityIndicator deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("Invalid KMIP tag for CriticalityIndicator"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("Failed to parse KMIP tag for CriticalityIndicator: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class,
                    String.format("Expected object with %s tag for CriticalityIndicator, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, String.format("Missing or non-text 'type' field for CriticalityIndicator"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isBoolean()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "CriticalityIndicator 'value' must be a boolean");
            return null;
        }

        boolean value = valueNode.asBoolean();
        CriticalityIndicator criticalityIndicator = CriticalityIndicator.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!criticalityIndicator.isSupported()) {
            ctxt.reportInputMismatch(CriticalityIndicator.class, "CriticalityIndicator not supported for spec " + spec);
            return null;
        }

        return criticalityIndicator;
    }
}