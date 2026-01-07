package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.InitialCounterValue;

import java.io.IOException;

public class InitialCounterValueJsonDeserializer extends KmipDataTypeJsonDeserializer<InitialCounterValue> {
    private final KmipTag kmipTag = InitialCounterValue.kmipTag;
    private final EncodingType encodingType = InitialCounterValue.encodingType;

    @Override
    public InitialCounterValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "JSON node cannot be null for InitialCounterValue deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(InitialCounterValue.class, "Invalid KMIP tag for InitialCounterValue");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(InitialCounterValue.class, String.format("Failed to parse KMIP tag for InitialCounterValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(InitialCounterValue.class,
                    String.format("Expected object with %s tag for InitialCounterValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "Missing or non-text 'type' field for InitialCounterValue");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "InitialCounterValue 'value' must be a number");
            return null;
        }

        Integer value = p.getCodec().treeToValue(valueNode, Integer.class);
        InitialCounterValue initialCounterValue = InitialCounterValue.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!initialCounterValue.isSupported()) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "InitialCounterValue not supported for spec " + spec);
            return null;
        }

        return initialCounterValue;
    }
}