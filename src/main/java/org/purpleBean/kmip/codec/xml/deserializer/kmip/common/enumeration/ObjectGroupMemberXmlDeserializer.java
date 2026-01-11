package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
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
    private final KmipTag kmipTag = ObjectGroupMember.kmipTag;
    private final EncodingType encodingType = ObjectGroupMember.encodingType;

    @Override
    public ObjectGroupMember deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Invalid Tag for ObjectGroupMember");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        String description = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ObjectGroupMember.class, "Missing or invalid 'type' attribute for ObjectGroupMember");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ObjectGroupMember.class,
                                "Missing or non-text 'value' for ObjectGroupMember");
                        return null;
                    }
                    description = p.getText();
                }
            }
        }

        if (description == null) {
            ctxt.reportInputMismatch(ObjectGroupMember.class, "Missing 'value' for ObjectGroupMember");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        ObjectGroupMember objectgroupmember = new ObjectGroupMember(ObjectGroupMember.fromName(description));
        if (!objectgroupmember.isSupported()) {
            throw new NoSuchElementException(
                    String.format("ObjectGroupMember '%s' not supported for spec %s", description, spec));
        }

        return objectgroupmember;
    }
}