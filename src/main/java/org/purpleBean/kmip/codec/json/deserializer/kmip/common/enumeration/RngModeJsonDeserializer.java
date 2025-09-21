package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RngMode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for RngMode.
 */
public class RngModeJsonDeserializer extends KmipDataTypeJsonDeserializer<RngMode> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.RNG_MODE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public RngMode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(RngMode.class, "JSON node cannot be null for RngMode deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RngMode.class, "Invalid KMIP tag for RngMode");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RngMode.class, String.format("Failed to parse KMIP tag for RngMode: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(RngMode.class,
                    String.format("Expected object with %s tag for RngMode, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RngMode.class, "Missing or non-text 'type' field for RngMode");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RngMode.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(RngMode.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        RngMode.Value rngmodeValue;
        try {
            rngmodeValue = RngMode.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(RngMode.class,
                    String.format("Unknown RngMode value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        RngMode rngmode = new RngMode(rngmodeValue);

        // Final validation: Ensure constructed RngMode is supported
        if (!rngmode.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("RngMode '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return rngmode;
    }
}
