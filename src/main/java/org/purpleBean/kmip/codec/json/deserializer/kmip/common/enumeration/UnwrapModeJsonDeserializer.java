package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for UnwrapMode.
 */
public class UnwrapModeJsonDeserializer extends KmipDataTypeJsonDeserializer<UnwrapMode> {
    private final KmipTag kmipTag = UnwrapMode.kmipTag;
    private final EncodingType encodingType = UnwrapMode.encodingType;

    @Override
    public UnwrapMode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(UnwrapMode.class, "JSON node cannot be null for UnwrapMode deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(UnwrapMode.class, "Invalid KMIP tag for UnwrapMode");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(UnwrapMode.class, String.format("Failed to parse KMIP tag for UnwrapMode: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(UnwrapMode.class,
                    String.format("Expected object with %s tag for UnwrapMode, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(UnwrapMode.class, "Missing or non-text 'type' field for UnwrapMode");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UnwrapMode.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(UnwrapMode.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        UnwrapMode.Value unwrapmodeValue;
        try {
            unwrapmodeValue = UnwrapMode.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(UnwrapMode.class,
                    String.format("Unknown UnwrapMode value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        UnwrapMode unwrapmode = new UnwrapMode(unwrapmodeValue);

        // Final validation: Ensure constructed UnwrapMode is supported
        if (!unwrapmode.isSupported()) {
            throw new NoSuchElementException(
                    String.format("UnwrapMode '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return unwrapmode;
    }
}
