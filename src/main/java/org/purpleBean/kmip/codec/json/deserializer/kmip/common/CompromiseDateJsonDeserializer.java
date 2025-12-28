package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.CompromiseDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for CompromiseDate.
 */
public class CompromiseDateJsonDeserializer extends KmipDataTypeJsonDeserializer<CompromiseDate> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.COMPROMISE_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public CompromiseDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(CompromiseDate.class, "JSON node cannot be null for CompromiseDate deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CompromiseDate.class, "Invalid KMIP tag for CompromiseDate");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CompromiseDate.class, String.format("Failed to parse KMIP tag for CompromiseDate: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CompromiseDate.class, "Expected object for CompromiseDate");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CompromiseDate.class, "Missing or non-text 'type' field for CompromiseDate");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(CompromiseDate.class, "Missing or non-text 'value' for CompromiseDate");
            return null;
        }

        OffsetDateTime value = OffsetDateTime.parse(valueNode.asText());
        CompromiseDate attribute = CompromiseDate.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("CompromiseDate '%s' is not supported for KMIP spec %s", valueNode.asText(), spec)
            );
        }
        return attribute;
    }
}