package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectClass, Integer> {

    public ObjectClassTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}