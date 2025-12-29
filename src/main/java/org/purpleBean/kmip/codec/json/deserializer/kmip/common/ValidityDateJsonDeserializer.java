package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ValidityDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ValidityDateJsonDeserializer extends KmipDataTypeJsonDeserializer<ValidityDate> {
    private final KmipTag kmipTag = ValidityDate.kmipTag;
    private final EncodingType encodingType = ValidityDate.encodingType;

    @Override
    public ValidityDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ValidityDate.class, String.format("JSON node cannot be null for ValidityDate deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ValidityDate.class, String.format("Invalid KMIP tag for ValidityDate"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ValidityDate.class, String.format("Failed to parse KMIP tag for ValidityDate: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ValidityDate.class,
                    String.format("Expected object with %s tag for ValidityDate, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ValidityDate.class, String.format("Missing or non-text 'type' field for ValidityDate"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ValidityDate.class, "ValidityDate 'value' must be a non-empty array");
            return null;
        }

        // TODO: update with required java type
        OffsetDateTime dateTime = p.getCodec().treeToValue(valueNode, OffsetDateTime.class);
        ValidityDate validityDate = ValidityDate.builder().value(dateTime).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!validityDate.isSupported()) {
            ctxt.reportInputMismatch(ValidityDate.class, "ValidityDate not supported for spec " + spec);
            return null;
        }

        return validityDate;
    }
}