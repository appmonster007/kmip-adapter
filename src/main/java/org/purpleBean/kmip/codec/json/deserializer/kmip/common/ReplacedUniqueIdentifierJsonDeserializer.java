package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;

import java.io.IOException;

public class ReplacedUniqueIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<ReplacedUniqueIdentifier> {
    private final KmipTag kmipTag = ReplacedUniqueIdentifier.kmipTag;
    private final EncodingType encodingType = ReplacedUniqueIdentifier.encodingType;

    @Override
    public ReplacedUniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, String.format("JSON node cannot be null for ReplacedUniqueIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, String.format("Invalid KMIP tag for ReplacedUniqueIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, String.format("Failed to parse KMIP tag for ReplacedUniqueIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class,
                    String.format("Expected object with %s tag for ReplacedUniqueIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, String.format("Missing or non-text 'type' field for ReplacedUniqueIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, "ReplacedUniqueIdentifier 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        ReplacedUniqueIdentifier replacedUniqueIdentifier = ReplacedUniqueIdentifier.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!replacedUniqueIdentifier.isSupported()) {
            ctxt.reportInputMismatch(ReplacedUniqueIdentifier.class, "ReplacedUniqueIdentifier not supported for spec " + spec);
            return null;
        }

        return replacedUniqueIdentifier;
    }
}