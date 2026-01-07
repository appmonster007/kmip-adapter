package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DataLength;

import java.io.IOException;

public class DataLengthJsonDeserializer extends KmipDataTypeJsonDeserializer<DataLength> {
    private final KmipTag kmipTag = DataLength.kmipTag;
    private final EncodingType encodingType = DataLength.encodingType;

    @Override
    public DataLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(DataLength.class, "JSON node cannot be null for DataLength deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DataLength.class, "Invalid KMIP tag for DataLength");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DataLength.class, String.format("Failed to parse KMIP tag for DataLength: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DataLength.class,
                    String.format("Expected object with %s tag for DataLength, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DataLength.class, "Missing or non-text 'type' field for DataLength");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(DataLength.class, "DataLength 'value' must be a non-empty numeric value");
            return null;
        }

        Integer value = p.getCodec().treeToValue(valueNode, Integer.class);
        DataLength dataLength = DataLength.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!dataLength.isSupported()) {
            ctxt.reportInputMismatch(DataLength.class, "DataLength not supported for spec " + spec);
            return null;
        }

        return dataLength;
    }
}
