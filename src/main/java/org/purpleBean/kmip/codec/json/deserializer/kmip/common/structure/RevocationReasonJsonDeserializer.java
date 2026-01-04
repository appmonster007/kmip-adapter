package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.structure.RevocationReason;

import java.io.IOException;
import java.util.NoSuchElementException;

public class RevocationReasonJsonDeserializer extends KmipDataTypeJsonDeserializer<RevocationReason> {
    private final KmipTag kmipTag = RevocationReason.kmipTag;
    private final EncodingType encodingType = RevocationReason.encodingType;

    @Override
    public RevocationReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(RevocationReason.class, "JSON node cannot be null for RevocationReason deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(RevocationReason.class, "Invalid KMIP tag for RevocationReason");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(RevocationReason.class, String.format("Failed to parse KMIP tag for RevocationReason: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(RevocationReason.class,
                    String.format("Expected object with %s tag for RevocationReason, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(RevocationReason.class, String.format("Missing or non-text 'type' field for RevocationReason"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(RevocationReason.class, "RevocationReason 'value' must be a non-empty array");
            return null;
        }

        RevocationReason.RevocationReasonBuilder builder = RevocationReason.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(RevocationReason.class, String.format("Failed to process field in RevocationReason: %s", e.getMessage()));
                return null;
            }
        }

        RevocationReason revocationReason = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!revocationReason.isSupported()) {
            throw new NoSuchElementException(String.format("RevocationReason is not supported for KMIP spec %s", spec));
        }

        return revocationReason;
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
            RevocationReason.RevocationReasonBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REVOCATION_REASON_CODE ->
                    builder.revocationReasonCode(p.getCodec().treeToValue(node, RevocationReasonCode.class));
            case KmipTag.Standard.REVOCATION_MESSAGE ->
                    builder.revocationMessage(p.getCodec().treeToValue(node, RevocationMessage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
