package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AsynchronousIndicator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for AsynchronousIndicator.
 */
public class AsynchronousIndicatorJsonDeserializer extends KmipDataTypeJsonDeserializer<AsynchronousIndicator> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ASYNCHRONOUS_INDICATOR);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public AsynchronousIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, "JSON node cannot be null for AsynchronousIndicator deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(AsynchronousIndicator.class, "Invalid KMIP tag for AsynchronousIndicator");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, String.format("Failed to parse KMIP tag for AsynchronousIndicator: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class,
                    String.format("Expected object with %s tag for AsynchronousIndicator, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, "Missing or non-text 'type' field for AsynchronousIndicator");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        AsynchronousIndicator.Value asynchronousindicatorValue;
        try {
            asynchronousindicatorValue = AsynchronousIndicator.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class,
                    String.format("Unknown AsynchronousIndicator value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        AsynchronousIndicator asynchronousindicator = new AsynchronousIndicator(asynchronousindicatorValue);

        // Final validation: Ensure constructed AsynchronousIndicator is supported
        if (!asynchronousindicator.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("AsynchronousIndicator '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return asynchronousindicator;
    }
}
