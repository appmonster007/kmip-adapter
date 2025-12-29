package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.UsageLimitsCount;

import java.io.IOException;

public class UsageLimitsCountJsonDeserializer extends KmipDataTypeJsonDeserializer<UsageLimitsCount> {
    private final KmipTag kmipTag = UsageLimitsCount.kmipTag;
    private final EncodingType encodingType = UsageLimitsCount.encodingType;

    @Override
    public UsageLimitsCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, String.format("JSON node cannot be null for UsageLimitsCount deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(UsageLimitsCount.class, String.format("Invalid KMIP tag for UsageLimitsCount"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, String.format("Failed to parse KMIP tag for UsageLimitsCount: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class,
                    String.format("Expected object with %s tag for UsageLimitsCount, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, String.format("Missing or non-text 'type' field for UsageLimitsCount"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "UsageLimitsCount 'value' must be a number");
            return null;
        }

        long value = valueNode.asLong();
        UsageLimitsCount usageLimitsCount = UsageLimitsCount.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!usageLimitsCount.isSupported()) {
            ctxt.reportInputMismatch(UsageLimitsCount.class, "UsageLimitsCount not supported for spec " + spec);
            return null;
        }

        return usageLimitsCount;
    }
}