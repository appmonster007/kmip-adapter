package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ExtensionType;

import java.io.IOException;

public class ExtensionTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<ExtensionType> {
    private final KmipTag kmipTag = ExtensionType.kmipTag;
    private final EncodingType encodingType = ExtensionType.encodingType;

    @Override
    public ExtensionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ExtensionType.class, String.format("JSON node cannot be null for ExtensionType deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ExtensionType.class, String.format("Invalid KMIP tag for ExtensionType"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ExtensionType.class, String.format("Failed to parse KMIP tag for ExtensionType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ExtensionType.class,
                    String.format("Expected object with %s tag for ExtensionType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ExtensionType.class, String.format("Missing or non-text 'type' field for ExtensionType"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(ExtensionType.class, "ExtensionType 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        ExtensionType extensionType = ExtensionType.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!extensionType.isSupported()) {
            ctxt.reportInputMismatch(ExtensionType.class, "ExtensionType not supported for spec " + spec);
            return null;
        }

        return extensionType;
    }
}