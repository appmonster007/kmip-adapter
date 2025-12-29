package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MaximumItems;

import java.io.IOException;

public class MaximumItemsJsonDeserializer extends KmipDataTypeJsonDeserializer<MaximumItems> {
    private final KmipTag kmipTag = MaximumItems.kmipTag;
    private final EncodingType encodingType = MaximumItems.encodingType;

    @Override
    public MaximumItems deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(MaximumItems.class, String.format("JSON node cannot be null for MaximumItems deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MaximumItems.class, String.format("Invalid KMIP tag for MaximumItems"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MaximumItems.class, String.format("Failed to parse KMIP tag for MaximumItems: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(MaximumItems.class,
                    String.format("Expected object with %s tag for MaximumItems, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MaximumItems.class, String.format("Missing or non-text 'type' field for MaximumItems"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(MaximumItems.class, "MaximumItems 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        MaximumItems maximumItems = MaximumItems.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!maximumItems.isSupported()) {
            ctxt.reportInputMismatch(MaximumItems.class, "MaximumItems not supported for spec " + spec);
            return null;
        }

        return maximumItems;
    }
}