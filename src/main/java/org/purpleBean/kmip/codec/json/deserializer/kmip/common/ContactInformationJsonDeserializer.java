package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ContactInformation;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class ContactInformationJsonDeserializer extends KmipDataTypeJsonDeserializer<ContactInformation> {
    private final KmipTag kmipTag = ContactInformation.kmipTag;
    private final EncodingType encodingType = ContactInformation.encodingType;

    @Override
    public ContactInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (node == null) {
            ctxt.reportInputMismatch(ContactInformation.class, String.format("JSON node cannot be null for ContactInformation deserialization"));
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ContactInformation.class, String.format("Invalid KMIP tag for ContactInformation"));
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ContactInformation.class, String.format("Failed to parse KMIP tag for ContactInformation: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ContactInformation.class,
                    String.format("Expected object with %s tag for ContactInformation, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ContactInformation.class, String.format("Missing or non-text 'type' field for ContactInformation"));
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ContactInformation.class, "ContactInformation 'value' must be a non-empty array");
            return null;
        }

        ContactInformation contactInformation = ContactInformation.builder().value(valueNode.asText()).build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();

        if (!contactInformation.isSupported()) {
            ctxt.reportInputMismatch(ContactInformation.class, "ContactInformation not supported for spec " + spec);
            return null;
        }

        return contactInformation;
    }
}
