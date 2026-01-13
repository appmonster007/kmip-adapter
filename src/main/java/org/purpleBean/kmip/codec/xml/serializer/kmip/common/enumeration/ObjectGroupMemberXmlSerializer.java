package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectGroupMember, String> {

    public ObjectGroupMemberXmlSerializer() {
        super(ObjectGroupMember::getDescription);
    }
}