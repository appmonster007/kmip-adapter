package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectGroupMember, String> {

    public ObjectGroupMemberJsonDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, String.class, value -> new ObjectGroupMember(ObjectGroupMember.fromName(value)));
    }
}