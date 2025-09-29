package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for RotateNameType.
 */
public class RotateNameTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<RotateNameType> {
    private final KmipTag kmipTag = RotateNameType.kmipTag;
    private final EncodingType encodingType = RotateNameType.encodingType;

    @Override
    public RotateNameType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(RotateNameType.class, "JSON node cannot be null for RotateNameType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RotateNameType.class, "Invalid KMIP tag for RotateNameType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RotateNameType.class, String.format("Failed to parse KMIP tag for RotateNameType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(RotateNameType.class,
                    String.format("Expected object with %s tag for RotateNameType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RotateNameType.class, "Missing or non-text 'type' field for RotateNameType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RotateNameType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(RotateNameType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        RotateNameType.Value rotatenametypeValue;
        try {
            rotatenametypeValue = RotateNameType.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(RotateNameType.class,
                    String.format("Unknown RotateNameType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        RotateNameType rotatenametype = new RotateNameType(rotatenametypeValue);

        // Final validation: Ensure constructed RotateNameType is supported
        if (!rotatenametype.isSupported()) {
            throw new NoSuchElementException(
                    String.format("RotateNameType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return rotatenametype;
    }
}
