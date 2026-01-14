package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectGroupMember;

public class ObjectGroupMemberTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectGroupMember, Integer> {

    public ObjectGroupMemberTtlvSerializer() {
        super(ObjectGroupMember::getValue);
    }
}