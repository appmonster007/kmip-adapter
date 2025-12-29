package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectDistinguishedNameJsonDeserializer extends KmipDataTypeJsonDeserializer<SubjectDistinguishedName> {
    private final KmipTag kmipTag = SubjectDistinguishedName.kmipTag;
    private final EncodingType encodingType = SubjectDistinguishedName.encodingType;

    @Override
    public SubjectDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, String.format("JSON node cannot be null for SubjectDistinguishedName deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(SubjectDistinguishedName.class, String.format("Invalid KMIP tag for SubjectDistinguishedName"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, String.format("Failed to parse KMIP tag for SubjectDistinguishedName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class,
                    String.format("Expected object with %s tag for SubjectDistinguishedName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, String.format("Missing or non-text 'type' field for SubjectDistinguishedName"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "SubjectDistinguishedName 'value' must be a non-empty array");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        SubjectDistinguishedName subjectDistinguishedName = SubjectDistinguishedName.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!subjectDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "SubjectDistinguishedName not supported for spec " + spec);
            return null;
        }

        return subjectDistinguishedName;
    }
}