package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.InvocationFieldLength;

import java.io.IOException;

public class InvocationFieldLengthJsonDeserializer extends KmipDataTypeJsonDeserializer<InvocationFieldLength> {
    private final KmipTag kmipTag = InvocationFieldLength.kmipTag;
    private final EncodingType encodingType = InvocationFieldLength.encodingType;

    @Override
    public InvocationFieldLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "JSON node cannot be null for InvocationFieldLength deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(InvocationFieldLength.class, "Invalid KMIP tag for InvocationFieldLength");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, String.format("Failed to parse KMIP tag for InvocationFieldLength: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class,
                    String.format("Expected object with %s tag for InvocationFieldLength, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "Missing or non-text 'type' field for InvocationFieldLength");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "InvocationFieldLength 'value' must be a number");
            return null;
        }

        Integer value = p.getCodec().treeToValue(valueNode, Integer.class);
        InvocationFieldLength invocationFieldLength = InvocationFieldLength.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!invocationFieldLength.isSupported()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "InvocationFieldLength not supported for spec " + spec);
            return null;
        }

        return invocationFieldLength;
    }
}