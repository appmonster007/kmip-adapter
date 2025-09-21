package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for MaskGenerator.
 */
public class MaskGeneratorJsonDeserializer extends KmipDataTypeJsonDeserializer<MaskGenerator> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.MASK_GENERATOR);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public MaskGenerator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(MaskGenerator.class, "JSON node cannot be null for MaskGenerator deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MaskGenerator.class, "Invalid KMIP tag for MaskGenerator");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MaskGenerator.class, String.format("Failed to parse KMIP tag for MaskGenerator: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(MaskGenerator.class,
                    String.format("Expected object with %s tag for MaskGenerator, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MaskGenerator.class, "Missing or non-text 'type' field for MaskGenerator");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MaskGenerator.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(MaskGenerator.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        MaskGenerator.Value maskgeneratorValue;
        try {
            maskgeneratorValue = MaskGenerator.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(MaskGenerator.class,
                    String.format("Unknown MaskGenerator value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        MaskGenerator maskgenerator = new MaskGenerator(maskgeneratorValue);

        // Final validation: Ensure constructed MaskGenerator is supported
        if (!maskgenerator.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("MaskGenerator '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return maskgenerator;
    }
}
