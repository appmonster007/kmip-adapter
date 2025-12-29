package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PublicKeyUniqueIdentifier;

import java.io.IOException;

public class PublicKeyUniqueIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<PublicKeyUniqueIdentifier> {
    private final KmipTag kmipTag = PublicKeyUniqueIdentifier.kmipTag;
    private final EncodingType encodingType = PublicKeyUniqueIdentifier.encodingType;

    @Override
    public PublicKeyUniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, String.format("JSON node cannot be null for PublicKeyUniqueIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, String.format("Invalid KMIP tag for PublicKeyUniqueIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, String.format("Failed to parse KMIP tag for PublicKeyUniqueIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class,
                    String.format("Expected object with %s tag for PublicKeyUniqueIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, String.format("Missing or non-text 'type' field for PublicKeyUniqueIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "PublicKeyUniqueIdentifier 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        PublicKeyUniqueIdentifier publicKeyUniqueIdentifier = PublicKeyUniqueIdentifier.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!publicKeyUniqueIdentifier.isSupported()) {
            ctxt.reportInputMismatch(PublicKeyUniqueIdentifier.class, "PublicKeyUniqueIdentifier not supported for spec " + spec);
            return null;
        }

        return publicKeyUniqueIdentifier;
    }
}