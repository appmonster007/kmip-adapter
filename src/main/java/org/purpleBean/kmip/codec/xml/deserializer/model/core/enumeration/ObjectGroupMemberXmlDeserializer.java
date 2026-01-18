package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectGroupMember, String> {

    public ObjectGroupMemberXmlDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, String.class, value -> new ObjectGroupMember(ObjectGroupMember.fromName(value)));
    }
}