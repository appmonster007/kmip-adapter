package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ObjectGroupMember.
 */
public class ObjectGroupMemberXmlDeserializer extends KmipDataTypeXmlDeserializer<ObjectGroupMember> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OBJECT_GROUP_MEMBER);

    @Override
    public ObjectGroupMember deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Expected XML element object for ObjectGroupMember");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Invalid Tag for ObjectGroupMember");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Missing or invalid '@type' attribute for ObjectGroupMember");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Missing or non-text '@value' attribute for ObjectGroupMember");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ObjectGroupMember objectgroupmember = new ObjectGroupMember(ObjectGroupMember.fromName(spec, description));
        if (!objectgroupmember.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("ObjectGroupMember '%s' not supported for spec %s", description, spec));
        }

        return objectgroupmember;
    }
}
