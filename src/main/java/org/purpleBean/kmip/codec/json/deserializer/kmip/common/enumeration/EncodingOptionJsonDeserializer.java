package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.EncodingOption;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for EncodingOption.
 */
public class EncodingOptionJsonDeserializer extends KmipDataTypeJsonDeserializer<EncodingOption> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ENCODING_OPTION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public EncodingOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(EncodingOption.class, String.format("JSON node cannot be null for EncodingOption deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(EncodingOption.class, String.format("Invalid KMIP tag for EncodingOption"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(EncodingOption.class, String.format("Failed to parse KMIP tag for EncodingOption: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(EncodingOption.class,
                    String.format("Expected object with %s tag for EncodingOption, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(EncodingOption.class, String.format("Missing or non-text 'type' field for EncodingOption"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(EncodingOption.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(EncodingOption.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        EncodingOption.Value encodingoptionValue;
        try {
            encodingoptionValue = EncodingOption.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(EncodingOption.class,
                    String.format("Unknown EncodingOption value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        EncodingOption encodingoption = new EncodingOption(encodingoptionValue);

        // Final validation: Ensure constructed EncodingOption is supported
        if (!encodingoption.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("EncodingOption '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return encodingoption;
    }
}
