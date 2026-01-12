package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupTtlvSerializer extends AbstractKmipTtlvSerializer<ObjectGroup, String> {

    public ObjectGroupTtlvSerializer() {
        super(ObjectGroup::getValue);
    }
}