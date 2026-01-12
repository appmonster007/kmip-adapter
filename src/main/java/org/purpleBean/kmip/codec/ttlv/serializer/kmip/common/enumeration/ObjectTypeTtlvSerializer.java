package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeTtlvSerializer extends AbstractKmipTtlvSerializer<ObjectType, Integer> {

    public ObjectTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}