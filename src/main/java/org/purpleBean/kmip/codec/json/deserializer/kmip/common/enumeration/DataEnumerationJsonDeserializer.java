package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DataEnumeration;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for DataEnumeration.
 */
public class DataEnumerationJsonDeserializer extends KmipDataTypeJsonDeserializer<DataEnumeration> {
    private final KmipTag kmipTag = DataEnumeration.kmipTag;
    private final EncodingType encodingType = DataEnumeration.encodingType;

    @Override
    public DataEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(DataEnumeration.class, "JSON node cannot be null for DataEnumeration deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DataEnumeration.class, "Invalid KMIP tag for DataEnumeration");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DataEnumeration.class, String.format("Failed to parse KMIP tag for DataEnumeration: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DataEnumeration.class,
                    String.format("Expected object with %s tag for DataEnumeration, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DataEnumeration.class, "Missing or non-text 'type' field for DataEnumeration");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DataEnumeration.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(DataEnumeration.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        DataEnumeration.Value dataenumerationValue;
        try {
            dataenumerationValue = DataEnumeration.fromName(description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(DataEnumeration.class,
                    String.format("Unknown DataEnumeration value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        DataEnumeration dataenumeration = new DataEnumeration(dataenumerationValue);

        // Final validation: Ensure constructed DataEnumeration is supported
        if (!dataenumeration.isSupported()) {
            throw new NoSuchElementException(
                    String.format("DataEnumeration '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return dataenumeration;
    }
}
