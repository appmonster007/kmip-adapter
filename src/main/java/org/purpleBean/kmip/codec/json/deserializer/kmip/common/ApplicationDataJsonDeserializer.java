package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ApplicationData;

import java.io.IOException;

public class ApplicationDataJsonDeserializer extends KmipDataTypeJsonDeserializer<ApplicationData> {
    private final KmipTag kmipTag = ApplicationData.kmipTag;
    private final EncodingType encodingType = ApplicationData.encodingType;

    @Override
    public ApplicationData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ApplicationData.class, String.format("JSON node cannot be null for ApplicationData deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ApplicationData.class, String.format("Invalid KMIP tag for ApplicationData"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ApplicationData.class, String.format("Failed to parse KMIP tag for ApplicationData: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ApplicationData.class,
                    String.format("Expected object with %s tag for ApplicationData, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ApplicationData.class, String.format("Missing or non-text 'type' field for ApplicationData"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ApplicationData.class, "ApplicationData 'value' must be a non-empty array");
            return null;
        }

        String value = valueNode.asText();
        ApplicationData applicationData = ApplicationData.builder().value(value).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!applicationData.isSupported()) {
            ctxt.reportInputMismatch(ApplicationData.class, "ApplicationData not supported for spec " + spec);
            return null;
        }

        return applicationData;
    }
}