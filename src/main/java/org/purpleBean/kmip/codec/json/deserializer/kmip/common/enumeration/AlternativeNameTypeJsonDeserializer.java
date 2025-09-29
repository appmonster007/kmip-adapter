package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for AlternativeNameType.
 */
public class AlternativeNameTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<AlternativeNameType> {
    private final KmipTag kmipTag = AlternativeNameType.kmipTag;
    private final EncodingType encodingType = AlternativeNameType.encodingType;

    @Override
    public AlternativeNameType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(AlternativeNameType.class, "JSON node cannot be null for AlternativeNameType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AlternativeNameType.class, "Invalid KMIP tag for AlternativeNameType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AlternativeNameType.class, String.format("Failed to parse KMIP tag for AlternativeNameType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(AlternativeNameType.class,
                    String.format("Expected object with %s tag for AlternativeNameType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AlternativeNameType.class, "Missing or non-text 'type' field for AlternativeNameType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AlternativeNameType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(AlternativeNameType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        AlternativeNameType.Value alternativenametypeValue;
        try {
            alternativenametypeValue = AlternativeNameType.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(AlternativeNameType.class,
                    String.format("Unknown AlternativeNameType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        AlternativeNameType alternativenametype = new AlternativeNameType(alternativenametypeValue);

        // Final validation: Ensure constructed AlternativeNameType is supported
        if (!alternativenametype.isSupported()) {
            throw new NoSuchElementException(
                    String.format("AlternativeNameType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return alternativenametype;
    }
}
