package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;

public class AttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<Attribute> {
    private final KmipTag kmipTag = Attribute.kmipTag;
    private final EncodingType encodingType = Attribute.encodingType;

    @Override
    public Attribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Attribute.class, "JSON node cannot be null for Attribute deserialization");
            return null;
        }

        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Attribute.class, "Invalid KMIP tag for Attribute");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Attribute.class, String.format("Failed to parse KMIP tag for Attribute: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Attribute.class,
                    String.format("Expected object with %s tag for Attribute, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || EncodingType.fromName(typeNode.asText()).isEmpty() || EncodingType.fromName(typeNode.asText()).get() != encodingType) {
            ctxt.reportInputMismatch(Attribute.class, "Missing or non-text 'type' field for Attribute");
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.size() < 2) {
            ctxt.reportInputMismatch(Attribute.class, "Attribute 'value' must be a non-empty array");
            return null;
        }

        JsonNode attrNameNode = null;
        JsonNode attrIndexNode = null;
        JsonNode attrValueNode = null;
        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) continue;
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            switch (nodeTag) {
                case KmipTag.Standard.ATTRIBUTE_NAME -> attrNameNode = valueNode;
                case KmipTag.Standard.ATTRIBUTE_INDEX -> attrIndexNode = valueNode;
                case KmipTag.Standard.ATTRIBUTE_VALUE -> attrValueNode = valueNode;
                default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
            }
        }

        if (attrNameNode == null || attrValueNode == null) {
            throw new IllegalArgumentException("Missing required fields for Attribute");
        }

        if (!attrValueNode.has("type")) {
            throw new IllegalArgumentException("Missing 'type' field for Attribute");
        }

        KmipSpec spec = KmipContext.getSpec();

        AttributeName attrName = p.getCodec().treeToValue(attrNameNode, AttributeName.class);

        AttributeIndex attrIndex;
        if (attrIndexNode == null) {
            attrIndex = null;
        } else {
            attrIndex = p.getCodec().treeToValue(attrIndexNode, AttributeIndex.class);
        }

        AttributeValue attrValue = p.getCodec().treeToValue(attrValueNode, AttributeValue.class);

        Attribute attribute = Attribute.builder()
                .attributeName(attrName)
                .attributeIndex(attrIndex)
                .attributeValue(attrValue)
                .build();

        if (!attribute.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(Attribute.class, "Attribute not supported for spec " + spec);
            return null;
        }

        return attribute;
    }
}
