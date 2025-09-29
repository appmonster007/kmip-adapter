package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.WrappingMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for WrappingMethod.
 */
public class WrappingMethodJsonDeserializer extends KmipDataTypeJsonDeserializer<WrappingMethod> {
    private final KmipTag kmipTag = WrappingMethod.kmipTag;
    private final EncodingType encodingType = WrappingMethod.encodingType;

    @Override
    public WrappingMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(WrappingMethod.class, "JSON node cannot be null for WrappingMethod deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(WrappingMethod.class, "Invalid KMIP tag for WrappingMethod");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(WrappingMethod.class, String.format("Failed to parse KMIP tag for WrappingMethod: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(WrappingMethod.class,
                    String.format("Expected object with %s tag for WrappingMethod, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(WrappingMethod.class, "Missing or non-text 'type' field for WrappingMethod");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(WrappingMethod.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(WrappingMethod.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        WrappingMethod.Value wrappingmethodValue;
        try {
            wrappingmethodValue = WrappingMethod.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(WrappingMethod.class,
                    String.format("Unknown WrappingMethod value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        WrappingMethod wrappingmethod = new WrappingMethod(wrappingmethodValue);

        // Final validation: Ensure constructed WrappingMethod is supported
        if (!wrappingmethod.isSupported()) {
            throw new NoSuchElementException(
                    String.format("WrappingMethod '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return wrappingmethod;
    }
}
