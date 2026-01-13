package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectGroup, String> {

    public ObjectGroupTtlvSerializer() {
        super(ObjectGroup::getValue);
    }
}