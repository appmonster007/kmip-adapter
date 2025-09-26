package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.structure.Attribute;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for AttributeIndex.
 */
public class AttributeIndexJsonDeserializer extends KmipDataTypeJsonDeserializer<Attribute.AttributeIndex> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE_INDEX);
    private final EncodingType encodingType = EncodingType.INTEGER;

    @Override
    public Attribute.AttributeIndex deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, String.format("JSON node cannot be null for AttributeIndex deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Attribute.AttributeIndex.class, String.format("Invalid KMIP tag for AttributeIndex"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, String.format("Failed to parse KMIP tag for AttributeIndex: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, "Expected object for AttributeIndex");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, String.format("Missing or non-text 'type' field for AttributeIndex"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(Attribute.AttributeIndex.class, "Missing or non-text 'value' for AttributeIndex");
            return null;
        }

        int index = valueNode.asInt();
        Attribute.AttributeIndex data = Attribute.AttributeIndex.builder().index(index).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!data.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("AttributeIndex '%s' is not supported for KMIP spec %s", valueNode.asText(), spec)
            );
        }
        return data;
    }
}
