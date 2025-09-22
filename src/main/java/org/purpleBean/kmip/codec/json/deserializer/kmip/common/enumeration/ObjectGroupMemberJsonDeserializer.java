package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * JSON deserializer for ObjectGroupMember.
 */
public class ObjectGroupMemberJsonDeserializer extends KmipDataTypeJsonDeserializer<ObjectGroupMember> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OBJECT_GROUP_MEMBER);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public ObjectGroupMember deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "JSON node cannot be null for ObjectGroupMember deserialization");
            return null;
        }

        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(ObjectGroupMember.class, "Invalid KMIP tag for ObjectGroupMember");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, String.format("Failed to parse KMIP tag for ObjectGroupMember: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(ObjectGroupMember.class,
                    String.format("Expected object with %s tag for ObjectGroupMember, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Missing or non-text 'type' field for ObjectGroupMember");
            return null;
        }

        // Validation: Extract and validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // Validation: KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        ObjectGroupMember.Value objectgroupmemberValue;
        try {
            objectgroupmemberValue = ObjectGroupMember.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(ObjectGroupMember.class,
                    String.format("Unknown ObjectGroupMember value '%s' for KMIP spec %s", description, spec));
            return null;
        }

        ObjectGroupMember objectgroupmember = new ObjectGroupMember(objectgroupmemberValue);

        // Final validation: Ensure constructed ObjectGroupMember is supported
        if (!objectgroupmember.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("ObjectGroupMember '%s' is not supported for KMIP spec %s", description, spec)
            );
        }

        return objectgroupmember;
    }
}
