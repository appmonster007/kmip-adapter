package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectType, Integer> {

    public ObjectTypeTtlvSerializer() {
        super(ObjectType::getValue);
    }
}