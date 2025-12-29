package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

import java.io.IOException;

public class NetworkIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<NetworkIdentifier> {
    private final KmipTag kmipTag = NetworkIdentifier.kmipTag;
    private final EncodingType encodingType = NetworkIdentifier.encodingType;

    @Override
    public NetworkIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, String.format("JSON node cannot be null for NetworkIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(NetworkIdentifier.class, String.format("Invalid KMIP tag for NetworkIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, String.format("Failed to parse KMIP tag for NetworkIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class,
                    String.format("Expected object with %s tag for NetworkIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, String.format("Missing or non-text 'type' field for NetworkIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "NetworkIdentifier 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        NetworkIdentifier networkIdentifier = NetworkIdentifier.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!networkIdentifier.isSupported()) {
            ctxt.reportInputMismatch(NetworkIdentifier.class, "NetworkIdentifier not supported for spec " + spec);
            return null;
        }

        return networkIdentifier;
    }
}