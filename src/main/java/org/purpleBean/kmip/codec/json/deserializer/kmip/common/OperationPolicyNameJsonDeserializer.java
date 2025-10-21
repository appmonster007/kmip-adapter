package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.OperationPolicyName;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class OperationPolicyNameJsonDeserializer extends KmipDataTypeJsonDeserializer<OperationPolicyName> {
    private final KmipTag kmipTag = OperationPolicyName.kmipTag;
    private final EncodingType encodingType = OperationPolicyName.encodingType;

    @Override
    public OperationPolicyName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(OperationPolicyName.class, String.format("JSON node cannot be null for OperationPolicyName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(OperationPolicyName.class, String.format("Invalid KMIP tag for OperationPolicyName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(OperationPolicyName.class, String.format("Failed to parse KMIP tag for OperationPolicyName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(OperationPolicyName.class,
                    String.format("Expected object with %s tag for OperationPolicyName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(OperationPolicyName.class, String.format("Missing or non-text 'type' field for OperationPolicyName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OperationPolicyName.class, "OperationPolicyName 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        OperationPolicyName operationPolicyName = OperationPolicyName.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!operationPolicyName.isSupported()) {
            ctxt.reportInputMismatch(OperationPolicyName.class, "OperationPolicyName not supported for spec " + spec);
            return null;
        }

        return operationPolicyName;
    }
}
