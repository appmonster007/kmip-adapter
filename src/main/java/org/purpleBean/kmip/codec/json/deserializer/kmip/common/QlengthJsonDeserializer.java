package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Qlength;

import java.io.IOException;

public class QlengthJsonDeserializer extends KmipDataTypeJsonDeserializer<Qlength> {
    private final KmipTag kmipTag = Qlength.kmipTag;
    private final EncodingType encodingType = Qlength.encodingType;

    @Override
    public Qlength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(Qlength.class, String.format("JSON node cannot be null for Qlength deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(Qlength.class, String.format("Invalid KMIP tag for Qlength"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(Qlength.class, String.format("Failed to parse KMIP tag for Qlength: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(Qlength.class,
                    String.format("Expected object with %s tag for Qlength, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(Qlength.class, String.format("Missing or non-text 'type' field for Qlength"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isNumber()) {
            ctxt.reportInputMismatch(Qlength.class, "Qlength 'value' must be a number");
            return null;
        }

        int value = valueNode.asInt();
        Qlength qlength = Qlength.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!qlength.isSupported()) {
            ctxt.reportInputMismatch(Qlength.class, "Qlength not supported for spec " + spec);
            return null;
        }

        return qlength;
    }
}