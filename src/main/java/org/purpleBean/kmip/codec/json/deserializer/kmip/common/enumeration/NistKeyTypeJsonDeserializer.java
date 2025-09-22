package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.NistKeyType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for NistKeyType.
 */
public class NistKeyTypeJsonDeserializer extends KmipDataTypeJsonDeserializer<NistKeyType> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.NIST_KEY_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public NistKeyType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(NistKeyType.class, "JSON node cannot be null for NistKeyType deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(NistKeyType.class, "Invalid KMIP tag for NistKeyType");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(NistKeyType.class, String.format("Failed to parse KMIP tag for NistKeyType: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(NistKeyType.class,
                    String.format("Expected object with %s tag for NistKeyType, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(NistKeyType.class, "Missing or non-text 'type' field for NistKeyType");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NistKeyType.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(NistKeyType.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        NistKeyType.Value nistkeytypeValue;
        try {
            nistkeytypeValue = NistKeyType.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(NistKeyType.class,
                    String.format("Unknown NistKeyType value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        NistKeyType nistkeytype = new NistKeyType(nistkeytypeValue);

        // Final validation: Ensure constructed NistKeyType is supported
        if (!nistkeytype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("NistKeyType '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return nistkeytype;
    }
}
