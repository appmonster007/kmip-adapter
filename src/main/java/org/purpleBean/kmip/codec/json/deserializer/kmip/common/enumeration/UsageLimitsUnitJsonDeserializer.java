package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for UsageLimitsUnit.
 */
public class UsageLimitsUnitJsonDeserializer extends KmipDataTypeJsonDeserializer<UsageLimitsUnit> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.USAGE_LIMITS_UNIT);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public UsageLimitsUnit deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, "JSON node cannot be null for UsageLimitsUnit deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(UsageLimitsUnit.class, "Invalid KMIP tag for UsageLimitsUnit");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, String.format("Failed to parse KMIP tag for UsageLimitsUnit: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class,
                    String.format("Expected object with %s tag for UsageLimitsUnit, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, "Missing or non-text 'type' field for UsageLimitsUnit");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        UsageLimitsUnit.Value usagelimitsunitValue;
        try {
            usagelimitsunitValue = UsageLimitsUnit.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(UsageLimitsUnit.class,
                    String.format("Unknown UsageLimitsUnit value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        UsageLimitsUnit usagelimitsunit = new UsageLimitsUnit(usagelimitsunitValue);

        // Final validation: Ensure constructed UsageLimitsUnit is supported
        if (!usagelimitsunit.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("UsageLimitsUnit '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return usagelimitsunit;
    }
}
