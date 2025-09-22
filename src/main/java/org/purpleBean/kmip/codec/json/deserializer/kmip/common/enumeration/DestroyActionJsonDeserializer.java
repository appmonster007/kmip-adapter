package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.DestroyAction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for DestroyAction.
 */
public class DestroyActionJsonDeserializer extends KmipDataTypeJsonDeserializer<DestroyAction> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DESTROY_ACTION);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public DestroyAction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(DestroyAction.class, "JSON node cannot be null for DestroyAction deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(DestroyAction.class, "Invalid KMIP tag for DestroyAction");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(DestroyAction.class, String.format("Failed to parse KMIP tag for DestroyAction: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(DestroyAction.class,
                    String.format("Expected object with %s tag for DestroyAction, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(DestroyAction.class, "Missing or non-text 'type' field for DestroyAction");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DestroyAction.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(DestroyAction.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        DestroyAction.Value destroyactionValue;
        try {
            destroyactionValue = DestroyAction.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(DestroyAction.class,
                    String.format("Unknown DestroyAction value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        DestroyAction destroyaction = new DestroyAction(destroyactionValue);

        // Final validation: Ensure constructed DestroyAction is supported
        if (!destroyaction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("DestroyAction '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return destroyaction;
    }
}
