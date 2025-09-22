package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ValidationAuthorityType.
 */
public class ValidationAuthorityTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<ValidationAuthorityType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.VALIDATION_AUTHORITY_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ValidationAuthorityType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, "JSON node cannot be null for ValidationAuthorityType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ValidationAuthorityType.class, "Invalid KMIP tag for ValidationAuthorityType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, String.format("Failed to parse KMIP tag for ValidationAuthorityType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class,
                    String.format("Expected object with %s tag for ValidationAuthorityType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, "Missing or non-text 'type' field for ValidationAuthorityType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ValidationAuthorityType.Value validationauthoritytypeValue;
        try {
            validationauthoritytypeValue = ValidationAuthorityType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ValidationAuthorityType.class,
                    String.format("Unknown ValidationAuthorityType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ValidationAuthorityType validationauthoritytype = new ValidationAuthorityType(validationauthoritytypeValue);

        // Final validation: Ensure constructed ValidationAuthorityType is supported
        if (!validationauthoritytype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ValidationAuthorityType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return validationauthoritytype;
    }
}
