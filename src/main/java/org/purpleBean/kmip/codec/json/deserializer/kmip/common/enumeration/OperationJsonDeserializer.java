package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.Operation;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for Operation.
 */
public class OperationJsonDeserializer extends KmipDataTypeJsonDeserializer<Operation> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OPERATION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public Operation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(Operation.class, "JSON node cannot be null for Operation deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Operation.class, "Invalid KMIP tag for Operation");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Operation.class, String.format("Failed to parse KMIP tag for Operation: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(Operation.class,
                    String.format("Expected object with %s tag for Operation, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Operation.class, "Missing or non-text 'type' field for Operation");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Operation.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(Operation.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        Operation.Value operationValue;
        try {
            operationValue = Operation.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(Operation.class,
                    String.format("Unknown Operation value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        Operation operation = new Operation(operationValue);

        // Final validation: Ensure constructed Operation is supported
        if (!operation.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("Operation '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return operation;
    }
}
