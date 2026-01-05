package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ApplicationData;
import org.purpleBean.kmip.common.ApplicationNamespace;
import org.purpleBean.kmip.common.structure.ApplicationSpecificInformation;

import java.io.IOException;
import java.util.NoSuchElementException;

public class ApplicationSpecificInformationJsonDeserializer extends KmipDataTypeJsonDeserializer<ApplicationSpecificInformation> {
    private final KmipTag kmipTag = ApplicationSpecificInformation.kmipTag;
    private final EncodingType encodingType = ApplicationSpecificInformation.encodingType;

    @Override
    public ApplicationSpecificInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "JSON node cannot be null for ApplicationSpecificInformation deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "Invalid KMIP tag for ApplicationSpecificInformation");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, String.format("Failed to parse KMIP tag for ApplicationSpecificInformation: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class,
                    String.format("Expected object with %s tag for ApplicationSpecificInformation, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, String.format("Missing or non-text 'type' field for ApplicationSpecificInformation"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(ApplicationSpecificInformation.class, "ApplicationSpecificInformation 'value' must be a non-empty array");
            return null;
        }

        ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder = ApplicationSpecificInformation.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(ApplicationSpecificInformation.class, String.format("Failed to process field in ApplicationSpecificInformation: %s", e.getMessage()));
                return null;
            }
        }

        ApplicationSpecificInformation applicationspecificinformation = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!applicationspecificinformation.isSupported()) {
            throw new NoSuchElementException(String.format("ApplicationSpecificInformation is not supported for KMIP spec %s", spec));
        }

        return applicationspecificinformation;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            ApplicationSpecificInformation.ApplicationSpecificInformationBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.APPLICATION_NAMESPACE ->
                    builder.applicationNamespace(p.getCodec().treeToValue(node, ApplicationNamespace.class));
            case KmipTag.Standard.APPLICATION_DATA ->
                    builder.applicationData(p.getCodec().treeToValue(node, ApplicationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}