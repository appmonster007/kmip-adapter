package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SignatureData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SignatureDataJsonDeserializer extends KmipDataTypeJsonDeserializer<SignatureData> {
    private final KmipTag kmipTag = SignatureData.kmipTag;
    private final EncodingType encodingType = SignatureData.encodingType;

    @Override
    public SignatureData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(SignatureData.class, "JSON node cannot be null for SignatureData deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SignatureData.class, "Invalid KMIP tag for SignatureData");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SignatureData.class, String.format("Failed to parse KMIP tag for SignatureData: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(SignatureData.class,
                    String.format("Expected object with %s tag for SignatureData, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SignatureData.class, "Missing or non-text 'type' field for SignatureData");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SignatureData.class, "SignatureData 'value' must be a non-empty textual value");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        SignatureData signatureData = SignatureData.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!signatureData.isSupported()) {
            ctxt.reportInputMismatch(SignatureData.class, "SignatureData not supported for spec " + spec);
            return null;
        }

        return signatureData;
    }
}
