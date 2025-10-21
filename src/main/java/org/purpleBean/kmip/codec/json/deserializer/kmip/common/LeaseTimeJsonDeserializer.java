package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.LeaseTime;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class LeaseTimeJsonDeserializer extends KmipDataTypeJsonDeserializer<LeaseTime> {
    private final KmipTag kmipTag = LeaseTime.kmipTag;
    private final EncodingType encodingType = LeaseTime.encodingType;

    @Override
    public LeaseTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(LeaseTime.class, String.format("JSON node cannot be null for LeaseTime deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(LeaseTime.class, String.format("Invalid KMIP tag for LeaseTime"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(LeaseTime.class, String.format("Failed to parse KMIP tag for LeaseTime: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(LeaseTime.class,
                    String.format("Expected object with %s tag for LeaseTime, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(LeaseTime.class, String.format("Missing or non-text 'type' field for LeaseTime"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(LeaseTime.class, "LeaseTime 'value' must be a non-empty array");
            return null;
        }

        LeaseTime leaseTime = LeaseTime.builder().value(valueNode.asInt()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!leaseTime.isSupported()) {
            ctxt.reportInputMismatch(LeaseTime.class, "LeaseTime not supported for spec " + spec);
            return null;
        }

        return leaseTime;
    }
}
