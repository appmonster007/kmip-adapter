package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for SplitKeyPolynomial.
 */
public class SplitKeyPolynomialJsonDeserializer extends KmipDataTypeJsonDeserializer<SplitKeyPolynomial> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SPLIT_KEY_POLYNOMIAL);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public SplitKeyPolynomial deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, "JSON node cannot be null for SplitKeyPolynomial deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SplitKeyPolynomial.class, "Invalid KMIP tag for SplitKeyPolynomial");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, String.format("Failed to parse KMIP tag for SplitKeyPolynomial: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class,
                    String.format("Expected object with %s tag for SplitKeyPolynomial, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, "Missing or non-text 'type' field for SplitKeyPolynomial");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        SplitKeyPolynomial.Value splitkeypolynomialValue;
        try {
            splitkeypolynomialValue = SplitKeyPolynomial.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(SplitKeyPolynomial.class,
                    String.format("Unknown SplitKeyPolynomial value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        SplitKeyPolynomial splitkeypolynomial = new SplitKeyPolynomial(splitkeypolynomialValue);

        // Final validation: Ensure constructed SplitKeyPolynomial is supported
        if (!splitkeypolynomial.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("SplitKeyPolynomial '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return splitkeypolynomial;
    }
}
