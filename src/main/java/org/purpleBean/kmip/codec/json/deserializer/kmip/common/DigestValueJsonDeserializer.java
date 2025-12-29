package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DigestValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DigestValueJsonDeserializer extends KmipDataTypeJsonDeserializer<DigestValue> {
    private final KmipTag kmipTag = DigestValue.kmipTag;
    private final EncodingType encodingType = DigestValue.encodingType;

    @Override
    public DigestValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(DigestValue.class, String.format("JSON node cannot be null for DigestValue deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DigestValue.class, String.format("Invalid KMIP tag for DigestValue"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DigestValue.class, String.format("Failed to parse KMIP tag for DigestValue: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DigestValue.class,
                    String.format("Expected object with %s tag for DigestValue, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DigestValue.class, String.format("Missing or non-text 'type' field for DigestValue"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DigestValue.class, "DigestValue 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        DigestValue digestValue = DigestValue.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!digestValue.isSupported()) {
            ctxt.reportInputMismatch(DigestValue.class, "DigestValue not supported for spec " + spec);
            return null;
        }

        return digestValue;
    }
}