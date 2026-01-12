package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassTtlvDeserializer extends AbstractKmipTtlvDeserializer<ObjectClass, Integer> {

    public ObjectClassTtlvDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType, Integer.class, value -> new ObjectClass(ObjectClass.fromValue(value)));
    }
}