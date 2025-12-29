package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SubjectAlternativeName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectAlternativeNameJsonDeserializer extends KmipDataTypeJsonDeserializer<SubjectAlternativeName> {
    private final KmipTag kmipTag = SubjectAlternativeName.kmipTag;
    private final EncodingType encodingType = SubjectAlternativeName.encodingType;

    @Override
    public SubjectAlternativeName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, String.format("JSON node cannot be null for SubjectAlternativeName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SubjectAlternativeName.class, String.format("Invalid KMIP tag for SubjectAlternativeName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, String.format("Failed to parse KMIP tag for SubjectAlternativeName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class,
                    String.format("Expected object with %s tag for SubjectAlternativeName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, String.format("Missing or non-text 'type' field for SubjectAlternativeName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, "SubjectAlternativeName 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        SubjectAlternativeName subjectAlternativeName = SubjectAlternativeName.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!subjectAlternativeName.isSupported()) {
            ctxt.reportInputMismatch(SubjectAlternativeName.class, "SubjectAlternativeName not supported for spec " + spec);
            return null;
        }

        return subjectAlternativeName;
    }
}