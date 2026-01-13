package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectGroupMember, String> {

    public ObjectGroupMemberJsonDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, String.class, value -> new ObjectGroupMember(ObjectGroupMember.fromName(value)));
    }
}