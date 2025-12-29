package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MACSignature;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MACSignatureJsonDeserializer extends KmipDataTypeJsonDeserializer<MACSignature> {
    private final KmipTag kmipTag = MACSignature.kmipTag;
    private final EncodingType encodingType = MACSignature.encodingType;

    @Override
    public MACSignature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(MACSignature.class, String.format("JSON node cannot be null for MACSignature deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MACSignature.class, String.format("Invalid KMIP tag for MACSignature"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MACSignature.class, String.format("Failed to parse KMIP tag for MACSignature: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(MACSignature.class,
                    String.format("Expected object with %s tag for MACSignature, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MACSignature.class, String.format("Missing or non-text 'type' field for MACSignature"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MACSignature.class, "MACSignature 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        MACSignature mACSignature = MACSignature.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!mACSignature.isSupported()) {
            ctxt.reportInputMismatch(MACSignature.class, "MACSignature not supported for spec " + spec);
            return null;
        }

        return mACSignature;
    }
}