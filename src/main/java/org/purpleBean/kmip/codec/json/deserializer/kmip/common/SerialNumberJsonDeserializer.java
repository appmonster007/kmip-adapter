package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SerialNumber;

import java.io.IOException;

public class SerialNumberJsonDeserializer extends KmipDataTypeJsonDeserializer<SerialNumber> {
    private final KmipTag kmipTag = SerialNumber.kmipTag;
    private final EncodingType encodingType = SerialNumber.encodingType;

    @Override
    public SerialNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(SerialNumber.class, String.format("JSON node cannot be null for SerialNumber deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SerialNumber.class, String.format("Invalid KMIP tag for SerialNumber"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SerialNumber.class, String.format("Failed to parse KMIP tag for SerialNumber: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(SerialNumber.class,
                    String.format("Expected object with %s tag for SerialNumber, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SerialNumber.class, String.format("Missing or non-text 'type' field for SerialNumber"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SerialNumber.class, "SerialNumber 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        SerialNumber serialNumber = SerialNumber.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!serialNumber.isSupported()) {
            ctxt.reportInputMismatch(SerialNumber.class, "SerialNumber not supported for spec " + spec);
            return null;
        }

        return serialNumber;
    }
}