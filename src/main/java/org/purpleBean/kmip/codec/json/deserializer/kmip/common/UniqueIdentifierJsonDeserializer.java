package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.UniqueIdentifier;

import java.io.IOException;
import java.util.NoSuchElementException;

public class UniqueIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<UniqueIdentifier> {
    private final KmipTag kmipTag = UniqueIdentifier.kmipTag;
    private final EncodingType encodingType = UniqueIdentifier.encodingType;

    @Override
    public UniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("JSON node cannot be null for UniqueIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("Invalid KMIP tag for UniqueIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("Failed to parse KMIP tag for UniqueIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class,
                    String.format("Expected object with %s tag for UniqueIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("Missing or non-text 'type' field for UniqueIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "UniqueIdentifier 'value' must be a non-empty array");
            return null;
        }

        String identifier = valueNode.asText();
        if (identifier == null || identifier.trim().isEmpty()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "UniqueIdentifier 'value' cannot be empty");
            return null;
        }
        UniqueIdentifier uniqueIdentifier = UniqueIdentifier.builder().value(identifier).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!uniqueIdentifier.isSupported()) {
            throw new NoSuchElementException(String.format("UniqueIdentifier is not supported for KMIP spec %s", spec));
        }

        return uniqueIdentifier;
    }
}
