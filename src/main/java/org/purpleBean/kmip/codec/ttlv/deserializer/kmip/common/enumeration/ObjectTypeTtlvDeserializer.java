package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectType, Integer> {

    public ObjectTypeTtlvDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType, Integer.class, value -> new ObjectType(ObjectType.fromValue(value)));
    }
}