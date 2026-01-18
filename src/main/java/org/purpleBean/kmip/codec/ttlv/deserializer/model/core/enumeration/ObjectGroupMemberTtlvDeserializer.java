package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectGroupMember, Integer> {

    public ObjectGroupMemberTtlvDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, Integer.class, value -> new ObjectGroupMember(ObjectGroupMember.fromValue(value)));
    }
}