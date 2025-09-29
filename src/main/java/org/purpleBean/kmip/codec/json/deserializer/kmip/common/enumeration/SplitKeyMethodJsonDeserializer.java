package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for SplitKeyMethod.
 */
public class SplitKeyMethodJsonDeserializer extends KmipDataTypeJsonDeserializer<SplitKeyMethod> {
    private final KmipTag kmipTag = SplitKeyMethod.kmipTag;
    private final EncodingType encodingType = SplitKeyMethod.encodingType;

    @Override
    public SplitKeyMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, "JSON node cannot be null for SplitKeyMethod deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SplitKeyMethod.class, "Invalid KMIP tag for SplitKeyMethod");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, String.format("Failed to parse KMIP tag for SplitKeyMethod: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(SplitKeyMethod.class,
                    String.format("Expected object with %s tag for SplitKeyMethod, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, "Missing or non-text 'type' field for SplitKeyMethod");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(SplitKeyMethod.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        SplitKeyMethod.Value splitkeymethodValue;
        try {
            splitkeymethodValue = SplitKeyMethod.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(SplitKeyMethod.class,
                    String.format("Unknown SplitKeyMethod value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        SplitKeyMethod splitkeymethod = new SplitKeyMethod(splitkeymethodValue);

        // Final validation: Ensure constructed SplitKeyMethod is supported
        if (!splitkeymethod.isSupported()) {
            throw new NoSuchElementException(
                    String.format("SplitKeyMethod '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return splitkeymethod;
    }
}
