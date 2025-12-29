package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DeviceIdentifier;

import java.io.IOException;

public class DeviceIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<DeviceIdentifier> {
    private final KmipTag kmipTag = DeviceIdentifier.kmipTag;
    private final EncodingType encodingType = DeviceIdentifier.encodingType;

    @Override
    public DeviceIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, String.format("JSON node cannot be null for DeviceIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DeviceIdentifier.class, String.format("Invalid KMIP tag for DeviceIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, String.format("Failed to parse KMIP tag for DeviceIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class,
                    String.format("Expected object with %s tag for DeviceIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, String.format("Missing or non-text 'type' field for DeviceIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "DeviceIdentifier 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        DeviceIdentifier deviceIdentifier = DeviceIdentifier.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!deviceIdentifier.isSupported()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "DeviceIdentifier not supported for spec " + spec);
            return null;
        }

        return deviceIdentifier;
    }
}