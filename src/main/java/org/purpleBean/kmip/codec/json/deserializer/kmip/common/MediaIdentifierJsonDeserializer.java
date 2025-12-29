package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MediaIdentifier;

import java.io.IOException;

public class MediaIdentifierJsonDeserializer extends KmipDataTypeJsonDeserializer<MediaIdentifier> {
    private final KmipTag kmipTag = MediaIdentifier.kmipTag;
    private final EncodingType encodingType = MediaIdentifier.encodingType;

    @Override
    public MediaIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(MediaIdentifier.class, String.format("JSON node cannot be null for MediaIdentifier deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MediaIdentifier.class, String.format("Invalid KMIP tag for MediaIdentifier"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MediaIdentifier.class, String.format("Failed to parse KMIP tag for MediaIdentifier: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(MediaIdentifier.class,
                    String.format("Expected object with %s tag for MediaIdentifier, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MediaIdentifier.class, String.format("Missing or non-text 'type' field for MediaIdentifier"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "MediaIdentifier 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        MediaIdentifier mediaIdentifier = MediaIdentifier.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!mediaIdentifier.isSupported()) {
            ctxt.reportInputMismatch(MediaIdentifier.class, "MediaIdentifier not supported for spec " + spec);
            return null;
        }

        return mediaIdentifier;
    }
}