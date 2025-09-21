package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.OpaqueDataType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for OpaqueDataType.
 */
public class OpaqueDataTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<OpaqueDataType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OPAQUE_DATA_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public OpaqueDataType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(OpaqueDataType.class, "JSON node cannot be null for OpaqueDataType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(OpaqueDataType.class, "Invalid KMIP tag for OpaqueDataType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(OpaqueDataType.class, String.format("Failed to parse KMIP tag for OpaqueDataType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(OpaqueDataType.class,
                    String.format("Expected object with %s tag for OpaqueDataType, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(OpaqueDataType.class, "Missing or non-text 'type' field for OpaqueDataType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OpaqueDataType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(OpaqueDataType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        OpaqueDataType.Value opaquedatatypeValue;
        try {
            opaquedatatypeValue = OpaqueDataType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(OpaqueDataType.class,
                    String.format("Unknown OpaqueDataType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        OpaqueDataType opaquedatatype = new OpaqueDataType(opaquedatatypeValue);

        // Final validation: Ensure constructed OpaqueDataType is supported
        if (!opaquedatatype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("OpaqueDataType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return opaquedatatype;
    }
}
