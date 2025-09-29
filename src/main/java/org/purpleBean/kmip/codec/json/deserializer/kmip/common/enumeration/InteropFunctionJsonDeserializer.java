package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.InteropFunction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for InteropFunction.
 */
public class InteropFunctionJsonDeserializer extends KmipDataTypeJsonDeserializer<InteropFunction> {
    private final KmipTag kmipTag = InteropFunction.kmipTag;
    private final EncodingType encodingType = InteropFunction.encodingType;

    @Override
    public InteropFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(InteropFunction.class, "JSON node cannot be null for InteropFunction deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(InteropFunction.class, "Invalid KMIP tag for InteropFunction");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(InteropFunction.class, String.format("Failed to parse KMIP tag for InteropFunction: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(InteropFunction.class,
                    String.format("Expected object with %s tag for InteropFunction, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(InteropFunction.class, "Missing or non-text 'type' field for InteropFunction");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(InteropFunction.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(InteropFunction.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        InteropFunction.Value interopfunctionValue;
        try {
            interopfunctionValue = InteropFunction.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(InteropFunction.class,
                    String.format("Unknown InteropFunction value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        InteropFunction interopfunction = new InteropFunction(interopfunctionValue);

        // Final validation: Ensure constructed InteropFunction is supported
        if (!interopfunction.isSupported()) {
            throw new NoSuchElementException(
                    String.format("InteropFunction '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return interopfunction;
    }
}
