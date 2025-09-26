package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;

public class AttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<Attribute> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE);
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public Attribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Attribute.class, String.format("JSON node cannot be null for Attribute deserialization"));
            return null;
        }

        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Attribute.class, String.format("Invalid KMIP tag for Attribute"));
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
            ctxt.reportInputMismatch(Attribute.class, String.format("Missing or non-text 'type' field for Attribute"));
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

        Attribute.AttributeName attrName = p.getCodec().treeToValue(attrNameNode, Attribute.AttributeName.class);
        String name = StringUtils.covertTitleToPascalCase(attrName.getName());

        Attribute.AttributeIndex attrIndex;
        if (attrIndexNode == null) {
            attrIndex = null;
        } else {
            attrIndex = p.getCodec().treeToValue(attrIndexNode, Attribute.AttributeIndex.class);
        }

        ObjectNode valueNode = ((ObjectNode) attrValueNode).put("name", name);

        Attribute.AttributeValue attrValue = p.getCodec().treeToValue(valueNode, Attribute.AttributeValue.class);

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
