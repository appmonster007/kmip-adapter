package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.FixedFieldLength;

import java.io.IOException;

public class FixedFieldLengthJsonDeserializer extends KmipDataTypeJsonDeserializer<FixedFieldLength> {
    private final KmipTag kmipTag = FixedFieldLength.kmipTag;
    private final EncodingType encodingType = FixedFieldLength.encodingType;

    @Override
    public FixedFieldLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "JSON node cannot be null for FixedFieldLength deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(FixedFieldLength.class, "Invalid KMIP tag for FixedFieldLength");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(FixedFieldLength.class, String.format("Failed to parse KMIP tag for FixedFieldLength: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(FixedFieldLength.class,
                    String.format("Expected object with %s tag for FixedFieldLength, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "Missing or non-text 'type' field for FixedFieldLength");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "FixedFieldLength 'value' must be a number");
            return null;
        }

        Integer value = p.getCodec().treeToValue(valueNode, Integer.class);
        FixedFieldLength fixedFieldLength = FixedFieldLength.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!fixedFieldLength.isSupported()) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "FixedFieldLength not supported for spec " + spec);
            return null;
        }

        return fixedFieldLength;
    }
}