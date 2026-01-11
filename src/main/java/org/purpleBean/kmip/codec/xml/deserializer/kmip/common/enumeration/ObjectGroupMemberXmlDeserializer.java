package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberXmlDeserializer extends AbstractKmipXmlDeserializer<ObjectGroupMember, String> {

    public ObjectGroupMemberXmlDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, String.class, value -> new ObjectGroupMember(ObjectGroupMember.fromName(value)));
    }
}