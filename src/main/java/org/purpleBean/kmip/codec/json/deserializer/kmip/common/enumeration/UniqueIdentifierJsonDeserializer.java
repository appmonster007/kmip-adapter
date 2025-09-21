package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for UniqueIdentifier.
 */
public class UniqueIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<UniqueIdentifier> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.UNIQUE_IDENTIFIER);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public UniqueIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "JSON node cannot be null for UniqueIdentifier deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(UniqueIdentifier.class, "Invalid KMIP tag for UniqueIdentifier");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("Failed to parse KMIP tag for UniqueIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class,
                    String.format("Expected object with %s tag for UniqueIdentifier, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, "Missing or non-text 'type' field for UniqueIdentifier");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(UniqueIdentifier.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        UniqueIdentifier.Value uniqueidentifierValue;
        try {
            uniqueidentifierValue = UniqueIdentifier.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(UniqueIdentifier.class,
                    String.format("Unknown UniqueIdentifier value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        UniqueIdentifier uniqueidentifier = new UniqueIdentifier(uniqueidentifierValue);

        // Final validation: Ensure constructed UniqueIdentifier is supported
        if (!uniqueidentifier.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("UniqueIdentifier '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return uniqueidentifier;
    }
}
