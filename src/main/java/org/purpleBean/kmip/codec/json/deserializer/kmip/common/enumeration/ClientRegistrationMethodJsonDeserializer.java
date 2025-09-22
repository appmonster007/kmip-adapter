package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ClientRegistrationMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ClientRegistrationMethod.
 */
public class ClientRegistrationMethodJsonDeserializer extends KmipDataTypeJsonDeserializer<ClientRegistrationMethod> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CLIENT_REGISTRATION_METHOD);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ClientRegistrationMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, "JSON node cannot be null for ClientRegistrationMethod deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ClientRegistrationMethod.class, "Invalid KMIP tag for ClientRegistrationMethod");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, String.format("Failed to parse KMIP tag for ClientRegistrationMethod: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class,
                    String.format("Expected object with %s tag for ClientRegistrationMethod, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, "Missing or non-text 'type' field for ClientRegistrationMethod");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ClientRegistrationMethod.Value clientregistrationmethodValue;
        try {
            clientregistrationmethodValue = ClientRegistrationMethod.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ClientRegistrationMethod.class,
                    String.format("Unknown ClientRegistrationMethod value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ClientRegistrationMethod clientregistrationmethod = new ClientRegistrationMethod(clientregistrationmethodValue);

        // Final validation: Ensure constructed ClientRegistrationMethod is supported
        if (!clientregistrationmethod.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ClientRegistrationMethod '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return clientregistrationmethod;
    }
}
