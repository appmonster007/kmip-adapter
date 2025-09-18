package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for QueryFunction.
 */
public class QueryFunctionJsonDeserializer extends KmipDataTypeJsonDeserializer<QueryFunction> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.QUERY_FUNCTION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public QueryFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(QueryFunction.class, String.format("JSON node cannot be null for QueryFunction deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(QueryFunction.class, String.format("Invalid KMIP tag for QueryFunction"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(QueryFunction.class, String.format("Failed to parse KMIP tag for QueryFunction: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(QueryFunction.class,
                    String.format("Expected object with %s tag for QueryFunction, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(QueryFunction.class, String.format("Missing or non-text 'type' field for QueryFunction"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(QueryFunction.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(QueryFunction.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        QueryFunction.Value queryfunctionValue;
        try {
            queryfunctionValue = QueryFunction.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(QueryFunction.class,
                    String.format("Unknown QueryFunction value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        QueryFunction queryfunction = new QueryFunction(queryfunctionValue);

        // Final validation: Ensure constructed QueryFunction is supported
        if (!queryfunction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("QueryFunction '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return queryfunction;
    }
}
