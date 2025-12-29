package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ExtensionTag;

import java.io.IOException;

public class ExtensionTagJsonDeserializer extends KmipDataTypeJsonDeserializer<ExtensionTag> {
    private final KmipTag kmipTag = ExtensionTag.kmipTag;
    private final EncodingType encodingType = ExtensionTag.encodingType;

    @Override
    public ExtensionTag deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ExtensionTag.class, String.format("JSON node cannot be null for ExtensionTag deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ExtensionTag.class, String.format("Invalid KMIP tag for ExtensionTag"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ExtensionTag.class, String.format("Failed to parse KMIP tag for ExtensionTag: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ExtensionTag.class,
                    String.format("Expected object with %s tag for ExtensionTag, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ExtensionTag.class, String.format("Missing or non-text 'type' field for ExtensionTag"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(ExtensionTag.class, "ExtensionTag 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        ExtensionTag extensionTag = ExtensionTag.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!extensionTag.isSupported()) {
            ctxt.reportInputMismatch(ExtensionTag.class, "ExtensionTag not supported for spec " + spec);
            return null;
        }

        return extensionTag;
    }
}