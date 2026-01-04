package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.common.structure.UsageLimits;

import java.io.IOException;
import java.util.NoSuchElementException;

public class UsageLimitsJsonDeserializer extends KmipDataTypeJsonDeserializer<UsageLimits> {
    private final KmipTag kmipTag = UsageLimits.kmipTag;
    private final EncodingType encodingType = UsageLimits.encodingType;

    @Override
    public UsageLimits deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(UsageLimits.class, "JSON node cannot be null for UsageLimits deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(UsageLimits.class, "Invalid KMIP tag for UsageLimits");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(UsageLimits.class, String.format("Failed to parse KMIP tag for UsageLimits: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(UsageLimits.class,
                    String.format("Expected object with %s tag for UsageLimits, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(UsageLimits.class, String.format("Missing or non-text 'type' field for UsageLimits"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(UsageLimits.class, "UsageLimits 'value' must be a non-empty array");
            return null;
        }

        UsageLimits.UsageLimitsBuilder builder = UsageLimits.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(UsageLimits.class, String.format("Failed to process field in UsageLimits: %s", e.getMessage()));
                return null;
            }
        }

        UsageLimits usageLimits = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!usageLimits.isSupported()) {
            throw new NoSuchElementException(String.format("UsageLimits is not supported for KMIP spec %s", spec));
        }

        return usageLimits;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            UsageLimits.UsageLimitsBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.USAGE_LIMITS_TOTAL ->
                    builder.usageLimitsTotal(p.getCodec().treeToValue(node, UsageLimitsTotal.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(p.getCodec().treeToValue(node, UsageLimitsCount.class));
            case KmipTag.Standard.USAGE_LIMITS_UNIT ->
                    builder.usageLimitsUnit(p.getCodec().treeToValue(node, UsageLimitsUnit.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
