package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.RandomIv;

import java.io.IOException;

public class RandomIvJsonDeserializer extends KmipDataTypeJsonDeserializer<RandomIv> {
    private final KmipTag kmipTag = RandomIv.kmipTag;
    private final EncodingType encodingType = RandomIv.encodingType;

    @Override
    public RandomIv deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(RandomIv.class, "JSON node cannot be null for RandomIv deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RandomIv.class, "Invalid KMIP tag for RandomIv");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RandomIv.class, String.format("Failed to parse KMIP tag for RandomIv: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(RandomIv.class,
                    String.format("Expected object with %s tag for RandomIv, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RandomIv.class, "Missing or non-text 'type' field for RandomIv");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isBoolean()) {
            ctxt.reportInputMismatch(RandomIv.class, "RandomIv 'value' must be a boolean");
            return null;
        }

        Boolean value = p.getCodec().treeToValue(valueNode, Boolean.class);
        RandomIv randomIv = RandomIv.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!randomIv.isSupported()) {
            ctxt.reportInputMismatch(RandomIv.class, "RandomIv not supported for spec " + spec);
            return null;
        }

        return randomIv;
    }
}