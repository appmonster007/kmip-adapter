package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.NameType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for NameType.
 */
public class NameTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<NameType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.NAME_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public NameType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(NameType.class, "JSON node cannot be null for NameType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(NameType.class, "Invalid KMIP tag for NameType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(NameType.class, String.format("Failed to parse KMIP tag for NameType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(NameType.class,
                    String.format("Expected object with %s tag for NameType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(NameType.class, "Missing or non-text 'type' field for NameType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NameType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(NameType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        NameType.Value nametypeValue;
        try {
            nametypeValue = NameType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(NameType.class,
                    String.format("Unknown NameType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        NameType nametype = new NameType(nametypeValue);

        // Final validation: Ensure constructed NameType is supported
        if (!nametype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("NameType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return nametype;
    }
}
