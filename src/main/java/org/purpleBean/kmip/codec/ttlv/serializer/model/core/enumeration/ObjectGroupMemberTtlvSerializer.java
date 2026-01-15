package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectGroupMember;

public class ObjectGroupMemberTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectGroupMember, Integer> {

    public ObjectGroupMemberTtlvSerializer() {
        super(ObjectGroupMember::getValue);
    }
}