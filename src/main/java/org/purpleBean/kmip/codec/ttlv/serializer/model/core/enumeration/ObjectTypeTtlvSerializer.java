package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectType, Integer> {

    public ObjectTypeTtlvSerializer() {
        super(ObjectType::getValue);
    }
}