package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectGroupMember, String> {

    public ObjectGroupMemberJsonSerializer() {
        super(ObjectGroupMember::getDescription);
    }
}