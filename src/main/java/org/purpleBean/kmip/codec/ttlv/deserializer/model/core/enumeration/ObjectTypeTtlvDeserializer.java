package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectType, Integer> {

    public ObjectTypeTtlvDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType, Integer.class, value -> new ObjectType(ObjectType.fromValue(value)));
    }
}