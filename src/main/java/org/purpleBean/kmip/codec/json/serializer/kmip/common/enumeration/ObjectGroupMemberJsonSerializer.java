package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberJsonSerializer extends AbstractKmipJsonSerializer<ObjectGroupMember, String> {

    public ObjectGroupMemberJsonSerializer() {
        super(ObjectGroupMember::getDescription);
    }
}