package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.PutFunction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for PutFunction.
 */
public class PutFunctionJsonDeserializer extends KmipDataTypeJsonDeserializer<PutFunction> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PUT_FUNCTION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public PutFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(PutFunction.class, "JSON node cannot be null for PutFunction deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(PutFunction.class, "Invalid KMIP tag for PutFunction");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(PutFunction.class, String.format("Failed to parse KMIP tag for PutFunction: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(PutFunction.class,
                    String.format("Expected object with %s tag for PutFunction, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(PutFunction.class, "Missing or non-text 'type' field for PutFunction");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PutFunction.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(PutFunction.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        PutFunction.Value putfunctionValue;
        try {
            putfunctionValue = PutFunction.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(PutFunction.class,
                    String.format("Unknown PutFunction value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        PutFunction putfunction = new PutFunction(putfunctionValue);

        // Final validation: Ensure constructed PutFunction is supported
        if (!putfunction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("PutFunction '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return putfunction;
    }
}
