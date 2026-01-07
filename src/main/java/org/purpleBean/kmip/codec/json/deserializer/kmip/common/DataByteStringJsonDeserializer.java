package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DataByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DataByteStringJsonDeserializer extends KmipDataTypeJsonDeserializer<DataByteString> {
    private final KmipTag kmipTag = DataByteString.kmipTag;
    private final EncodingType encodingType = DataByteString.encodingType;

    @Override
    public DataByteString deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(DataByteString.class, "JSON node cannot be null for DataByteString deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DataByteString.class, "Invalid KMIP tag for DataByteString");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DataByteString.class, String.format("Failed to parse KMIP tag for DataByteString: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DataByteString.class,
                    String.format("Expected object with %s tag for DataByteString, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DataByteString.class, "Missing or non-text 'type' field for DataByteString");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DataByteString.class, "DataByteString 'value' must be a non-empty textual value");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        DataByteString dataByteString = DataByteString.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!dataByteString.isSupported()) {
            ctxt.reportInputMismatch(DataByteString.class, "DataByteString not supported for spec " + spec);
            return null;
        }

        return dataByteString;
    }
}
