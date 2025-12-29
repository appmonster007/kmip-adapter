package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

import java.io.IOException;

public class MACSignatureKeyInformationJsonDeserializer extends KmipDataTypeJsonDeserializer<MACSignatureKeyInformation> {
    private final KmipTag kmipTag = MACSignatureKeyInformation.kmipTag;
    private final EncodingType encodingType = MACSignatureKeyInformation.encodingType;

    @Override
    public MACSignatureKeyInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, String.format("JSON node cannot be null for MACSignatureKeyInformation deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MACSignatureKeyInformation.class, String.format("Invalid KMIP tag for MACSignatureKeyInformation"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, String.format("Failed to parse KMIP tag for MACSignatureKeyInformation: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class,
                    String.format("Expected object with %s tag for MACSignatureKeyInformation, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, String.format("Missing or non-text 'type' field for MACSignatureKeyInformation"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "MACSignatureKeyInformation 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        MACSignatureKeyInformation mACSignatureKeyInformation = MACSignatureKeyInformation.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!mACSignatureKeyInformation.isSupported()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "MACSignatureKeyInformation not supported for spec " + spec);
            return null;
        }

        return mACSignatureKeyInformation;
    }
}