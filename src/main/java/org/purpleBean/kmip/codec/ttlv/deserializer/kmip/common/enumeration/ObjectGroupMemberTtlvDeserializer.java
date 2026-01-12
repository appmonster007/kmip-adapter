package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberTtlvDeserializer extends AbstractKmipTtlvDeserializer<ObjectGroupMember, Integer> {

    public ObjectGroupMemberTtlvDeserializer() {
        super(ObjectGroupMember.kmipTag, ObjectGroupMember.encodingType, Integer.class, value -> new ObjectGroupMember(ObjectGroupMember.fromValue(value)));
    }
}