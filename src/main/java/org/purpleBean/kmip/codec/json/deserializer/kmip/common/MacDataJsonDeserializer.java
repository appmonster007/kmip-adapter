package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.MacData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacDataJsonDeserializer extends KmipDataTypeJsonDeserializer<MacData> {
    private final KmipTag kmipTag = MacData.kmipTag;
    private final EncodingType encodingType = MacData.encodingType;

    @Override
    public MacData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(MacData.class, "JSON node cannot be null for MacData deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(MacData.class, "Invalid KMIP tag for MacData");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(MacData.class, String.format("Failed to parse KMIP tag for MacData: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(MacData.class,
                    String.format("Expected object with %s tag for MacData, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(MacData.class, "Missing or non-text 'type' field for MacData");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MacData.class, "MacData 'value' must be a non-empty textual value");
            return null;
        }

        ByteBuffer value = p.getCodec().treeToValue(valueNode, ByteBuffer.class);
        MacData macData = MacData.of(value);

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!macData.isSupported()) {
            ctxt.reportInputMismatch(MacData.class, "MacData not supported for spec " + spec);
            return null;
        }

        return macData;
    }
}
