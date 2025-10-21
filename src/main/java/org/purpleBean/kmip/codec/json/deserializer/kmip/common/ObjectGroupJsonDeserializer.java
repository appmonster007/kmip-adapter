package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ObjectGroup;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class ObjectGroupJsonDeserializer extends KmipDataTypeJsonDeserializer<ObjectGroup> {
    private final KmipTag kmipTag = ObjectGroup.kmipTag;
    private final EncodingType encodingType = ObjectGroup.encodingType;

    @Override
    public ObjectGroup deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ObjectGroup.class, String.format("JSON node cannot be null for ObjectGroup deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ObjectGroup.class, String.format("Invalid KMIP tag for ObjectGroup"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ObjectGroup.class, String.format("Failed to parse KMIP tag for ObjectGroup: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ObjectGroup.class,
                    String.format("Expected object with %s tag for ObjectGroup, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ObjectGroup.class, String.format("Missing or non-text 'type' field for ObjectGroup"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ObjectGroup.class, "ObjectGroup 'value' must be a non-empty array");
            return null;
        }

        ObjectGroup objectGroup = ObjectGroup.builder().value(valueNode.asText()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!objectGroup.isSupported()) {
            ctxt.reportInputMismatch(ObjectGroup.class, "ObjectGroup not supported for spec " + spec);
            return null;
        }

        return objectGroup;
    }
}
