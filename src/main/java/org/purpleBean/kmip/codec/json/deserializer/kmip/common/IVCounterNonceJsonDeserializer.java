package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.IVCounterNonce;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IVCounterNonceJsonDeserializer extends KmipDataTypeJsonDeserializer<IVCounterNonce> {
    private final KmipTag kmipTag = IVCounterNonce.kmipTag;
    private final EncodingType encodingType = IVCounterNonce.encodingType;

    @Override
    public IVCounterNonce deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(IVCounterNonce.class, String.format("JSON node cannot be null for IVCounterNonce deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(IVCounterNonce.class, String.format("Invalid KMIP tag for IVCounterNonce"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(IVCounterNonce.class, String.format("Failed to parse KMIP tag for IVCounterNonce: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(IVCounterNonce.class,
                    String.format("Expected object with %s tag for IVCounterNonce, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(IVCounterNonce.class, String.format("Missing or non-text 'type' field for IVCounterNonce"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "IVCounterNonce 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        IVCounterNonce iVCounterNonce = IVCounterNonce.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!iVCounterNonce.isSupported()) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "IVCounterNonce not supported for spec " + spec);
            return null;
        }

        return iVCounterNonce;
    }
}