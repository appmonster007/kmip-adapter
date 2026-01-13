package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectGroupMember, String> {

    public ObjectGroupMemberXmlDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, String.class, value -> new ObjectGroupMember(ObjectGroupMember.fromName(value)));
    }
}