package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ArchiveDateJsonDeserializer extends KmipDataTypeJsonDeserializer<ArchiveDate> {
    private final KmipTag kmipTag = ArchiveDate.kmipTag;
    private final EncodingType encodingType = ArchiveDate.encodingType;

    @Override
    public ArchiveDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ArchiveDate.class, String.format("JSON node cannot be null for ArchiveDate deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ArchiveDate.class, String.format("Invalid KMIP tag for ArchiveDate"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ArchiveDate.class, String.format("Failed to parse KMIP tag for ArchiveDate: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ArchiveDate.class,
                    String.format("Expected object with %s tag for ArchiveDate, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ArchiveDate.class, String.format("Missing or non-text 'type' field for ArchiveDate"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ArchiveDate.class, "ArchiveDate 'value' must be a non-empty array");
            return null;
        }

        // TODO: update with required java type
        OffsetDateTime dateTime = p.getCodec().treeToValue(valueNode, OffsetDateTime.class);
        ArchiveDate archiveDate = ArchiveDate.builder().value(dateTime).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!archiveDate.isSupported()) {
            ctxt.reportInputMismatch(ArchiveDate.class, "ArchiveDate not supported for spec " + spec);
            return null;
        }

        return archiveDate;
    }
}
