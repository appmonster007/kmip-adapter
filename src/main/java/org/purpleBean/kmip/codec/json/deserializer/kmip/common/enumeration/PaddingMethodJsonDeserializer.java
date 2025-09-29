package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for PaddingMethod.
 */
public class PaddingMethodJsonDeserializer extends KmipDataTypeJsonDeserializer<PaddingMethod> {
    private final KmipTag kmipTag = PaddingMethod.kmipTag;
    private final EncodingType encodingType = PaddingMethod.encodingType;

    @Override
    public PaddingMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(PaddingMethod.class, "JSON node cannot be null for PaddingMethod deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PaddingMethod.class, "Invalid KMIP tag for PaddingMethod");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PaddingMethod.class, String.format("Failed to parse KMIP tag for PaddingMethod: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PaddingMethod.class,
                    String.format("Expected object with %s tag for PaddingMethod, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PaddingMethod.class, "Missing or non-text 'type' field for PaddingMethod");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PaddingMethod.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(PaddingMethod.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        PaddingMethod.Value paddingmethodValue;
        try {
            paddingmethodValue = PaddingMethod.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(PaddingMethod.class,
                    String.format("Unknown PaddingMethod value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        PaddingMethod paddingmethod = new PaddingMethod(paddingmethodValue);

        // Final validation: Ensure constructed PaddingMethod is supported
        if (!paddingmethod.isSupported()) {
            throw new NoSuchElementException(
                    String.format("PaddingMethod '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return paddingmethod;
    }
}
