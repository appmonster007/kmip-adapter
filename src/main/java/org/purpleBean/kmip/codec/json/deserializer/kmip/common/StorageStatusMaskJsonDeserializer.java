package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.StorageStatusMask;

import java.io.IOException;

public class StorageStatusMaskJsonDeserializer extends KmipDataTypeJsonDeserializer<StorageStatusMask> {
    private final KmipTag kmipTag = StorageStatusMask.kmipTag;
    private final EncodingType encodingType = StorageStatusMask.encodingType;

    @Override
    public StorageStatusMask deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(StorageStatusMask.class, String.format("JSON node cannot be null for StorageStatusMask deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(StorageStatusMask.class, String.format("Invalid KMIP tag for StorageStatusMask"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(StorageStatusMask.class, String.format("Failed to parse KMIP tag for StorageStatusMask: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(StorageStatusMask.class,
                    String.format("Expected object with %s tag for StorageStatusMask, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(StorageStatusMask.class, String.format("Missing or non-text 'type' field for StorageStatusMask"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(StorageStatusMask.class, "StorageStatusMask 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        StorageStatusMask storageStatusMask = StorageStatusMask.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!storageStatusMask.isSupported()) {
            ctxt.reportInputMismatch(StorageStatusMask.class, "StorageStatusMask not supported for spec " + spec);
            return null;
        }

        return storageStatusMask;
    }
}