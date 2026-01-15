package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<ObjectClass, Integer> {

    public ObjectClassTtlvSerializer() {
        super(ObjectClass::getValue);
    }
}