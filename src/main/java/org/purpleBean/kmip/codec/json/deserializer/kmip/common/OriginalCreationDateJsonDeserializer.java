package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for OriginalCreationDate.
 */
public class OriginalCreationDateJsonDeserializer extends KmipDataTypeJsonDeserializer<OriginalCreationDate> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ORIGINAL_CREATION_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public OriginalCreationDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(OriginalCreationDate.class, "JSON node cannot be null for OriginalCreationDate deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(OriginalCreationDate.class, "Invalid KMIP tag for OriginalCreationDate");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(OriginalCreationDate.class, String.format("Failed to parse KMIP tag for OriginalCreationDate: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(OriginalCreationDate.class, "Expected object for OriginalCreationDate");
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(OriginalCreationDate.class, "Missing or non-text 'type' field for OriginalCreationDate");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OriginalCreationDate.class, "Missing or non-text 'value' for OriginalCreationDate");
            return null;
        }

        OffsetDateTime value = OffsetDateTime.parse(valueNode.asText());
        OriginalCreationDate attribute = OriginalCreationDate.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("OriginalCreationDate '%s' is not supported for KMIP spec %s", valueNode.asText(), spec)
            );
        }
        return attribute;
    }
}