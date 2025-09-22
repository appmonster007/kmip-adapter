package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ProfileName;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ProfileName.
 */
public class ProfileNameJsonDeserializer extends KmipDataTypeJsonDeserializer<ProfileName> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROFILE_NAME);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ProfileName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ProfileName.class, "JSON node cannot be null for ProfileName deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ProfileName.class, "Invalid KMIP tag for ProfileName");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ProfileName.class, String.format("Failed to parse KMIP tag for ProfileName: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ProfileName.class,
                    String.format("Expected object with %s tag for ProfileName, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ProfileName.class, "Missing or non-text 'type' field for ProfileName");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ProfileName.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ProfileName.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ProfileName.Value profilenameValue;
        try {
            profilenameValue = ProfileName.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ProfileName.class,
                    String.format("Unknown ProfileName value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ProfileName profilename = new ProfileName(profilenameValue);

        // Final validation: Ensure constructed ProfileName is supported
        if (!profilename.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ProfileName '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return profilename;
    }
}
