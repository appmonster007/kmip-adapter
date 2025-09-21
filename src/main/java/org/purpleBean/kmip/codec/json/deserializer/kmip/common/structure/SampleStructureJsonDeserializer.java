package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SampleStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<SampleStructure> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.SECRET_DATA);
    private final EncodingType encodingType = EncodingType.STRUCTURE;

    @Override
    public SampleStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(SampleStructure.class, "JSON node cannot be null for SampleStructure deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SampleStructure.class, "Invalid KMIP tag for SampleStructure");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SampleStructure.class, String.format("Failed to parse KMIP tag for SampleStructure: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(SampleStructure.class,
                    String.format("Expected object with %s tag for SampleStructure, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SampleStructure.class, "Missing or non-text 'type' field for SampleStructure");
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(SampleStructure.class, "SampleStructure 'value' must be a non-empty array");
            return null;
        }

        SampleStructure.SampleStructureBuilder builder = SampleStructure.builder();

        for (JsonNode valueNode : values) {
            if (!valueNode.has("tag")) continue;

            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).value();
            setValue(builder, nodeTag, valueNode, p, ctxt);
        }

        SampleStructure sampleStructure = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!sampleStructure.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("SampleStructure is not supported for KMIP spec %s", spec)
            );
        }

        return sampleStructure;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(SampleStructure.SampleStructureBuilder builder,
                          KmipTag.Value nodeTag,
                          JsonNode node,
                          JsonParser p,
                          DeserializationContext ctxt) throws IOException {
        // TODO: Implement field deserialization based on tag, preferably using switch case expression
        // Example:
        switch (nodeTag) {
            case KmipTag.Standard.ACTIVATION_DATE ->
                    builder.activationDate(p.getCodec().treeToValue(node, ActivationDateAttribute.class));
            case KmipTag.Standard.STATE -> builder.state(p.getCodec().treeToValue(node, State.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
