package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class LastChangeDateJsonDeserializer extends KmipDataTypeJsonDeserializer<LastChangeDate> {
    private final KmipTag kmipTag = LastChangeDate.kmipTag;
    private final EncodingType encodingType = LastChangeDate.encodingType;

    @Override
    public LastChangeDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(LastChangeDate.class, String.format("JSON node cannot be null for LastChangeDate deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(LastChangeDate.class, String.format("Invalid KMIP tag for LastChangeDate"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(LastChangeDate.class, String.format("Failed to parse KMIP tag for LastChangeDate: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(LastChangeDate.class,
                    String.format("Expected object with %s tag for LastChangeDate, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(LastChangeDate.class, String.format("Missing or non-text 'type' field for LastChangeDate"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(LastChangeDate.class, "LastChangeDate 'value' must be a non-empty array");
            return null;
        }

        // TODO: update with required java type
        OffsetDateTime dateTime = p.getCodec().treeToValue(valueNode, OffsetDateTime.class);
        LastChangeDate lastChangeDate = LastChangeDate.builder().value(dateTime).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!lastChangeDate.isSupported()) {
            ctxt.reportInputMismatch(LastChangeDate.class, "LastChangeDate not supported for spec " + spec);
            return null;
        }

        return lastChangeDate;
    }
}
