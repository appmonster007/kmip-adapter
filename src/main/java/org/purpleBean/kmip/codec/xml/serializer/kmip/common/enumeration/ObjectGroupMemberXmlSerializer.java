package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ObjectGroupMemberXmlSerializer extends AbstractKmipXmlSerializer<ObjectGroupMember, String> {

    public ObjectGroupMemberXmlSerializer() {
        super(ObjectGroupMember::getDescription);
    }
}