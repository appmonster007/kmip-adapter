package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.DestroyDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class DestroyDateJsonDeserializer extends KmipDataTypeJsonDeserializer<DestroyDate> {
    private final KmipTag kmipTag = DestroyDate.kmipTag;
    private final EncodingType encodingType = DestroyDate.encodingType;

    @Override
    public DestroyDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(DestroyDate.class, String.format("JSON node cannot be null for DestroyDate deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DestroyDate.class, String.format("Invalid KMIP tag for DestroyDate"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DestroyDate.class, String.format("Failed to parse KMIP tag for DestroyDate: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DestroyDate.class,
                    String.format("Expected object with %s tag for DestroyDate, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DestroyDate.class, String.format("Missing or non-text 'type' field for DestroyDate"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DestroyDate.class, "DestroyDate 'value' must be a non-empty array");
            return null;
        }

        // TODO: update with required java type
        OffsetDateTime dateTime = p.getCodec().treeToValue(valueNode, OffsetDateTime.class);
        DestroyDate destroyDate = DestroyDate.builder().value(dateTime).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!destroyDate.isSupportedFor(spec)) {
            throw new NoSuchElementException(String.format("DestroyDate is not supported for KMIP spec %s", spec));
        }

        return destroyDate;
    }
}
