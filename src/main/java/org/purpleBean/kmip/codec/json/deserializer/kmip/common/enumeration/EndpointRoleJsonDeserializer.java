package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for EndpointRole.
 */
public class EndpointRoleJsonDeserializer extends KmipDataTypeJsonDeserializer<EndpointRole> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ENDPOINT_ROLE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public EndpointRole deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(EndpointRole.class, "JSON node cannot be null for EndpointRole deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(EndpointRole.class, "Invalid KMIP tag for EndpointRole");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(EndpointRole.class, String.format("Failed to parse KMIP tag for EndpointRole: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.value().getValue() != kmipTag.value().getValue()) {
            ctxt.reportInputMismatch(EndpointRole.class,
                    String.format("Expected object with %s tag for EndpointRole, got tag: %s", kmipTag.value().getValue(), tag.value().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(EndpointRole.class, "Missing or non-text 'type' field for EndpointRole");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(EndpointRole.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(EndpointRole.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        EndpointRole.Value endpointroleValue;
        try {
            endpointroleValue = EndpointRole.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(EndpointRole.class,
                    String.format("Unknown EndpointRole value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        EndpointRole endpointrole = new EndpointRole(endpointroleValue);

        // Final validation: Ensure constructed EndpointRole is supported
        if (!endpointrole.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("EndpointRole '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return endpointrole;
    }
}
