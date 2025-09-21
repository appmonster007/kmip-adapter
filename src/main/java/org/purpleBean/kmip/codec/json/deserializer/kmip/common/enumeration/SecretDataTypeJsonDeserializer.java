package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.SecretDataType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for SecretDataType.
 */
public class SecretDataTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<SecretDataType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SECRET_DATA_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public SecretDataType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(SecretDataType.class, "JSON node cannot be null for SecretDataType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SecretDataType.class, "Invalid KMIP tag for SecretDataType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SecretDataType.class, String.format("Failed to parse KMIP tag for SecretDataType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(SecretDataType.class,
                    String.format("Expected object with %s tag for SecretDataType, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SecretDataType.class, "Missing or non-text 'type' field for SecretDataType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SecretDataType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(SecretDataType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        SecretDataType.Value secretdatatypeValue;
        try {
            secretdatatypeValue = SecretDataType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(SecretDataType.class,
                    String.format("Unknown SecretDataType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        SecretDataType secretdatatype = new SecretDataType(secretdatatypeValue);

        // Final validation: Ensure constructed SecretDataType is supported
        if (!secretdatatype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("SecretDataType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return secretdatatype;
    }
}
