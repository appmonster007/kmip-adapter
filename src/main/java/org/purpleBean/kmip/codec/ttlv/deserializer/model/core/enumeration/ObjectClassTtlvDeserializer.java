package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectClass, Integer> {

    public ObjectClassTtlvDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType, Integer.class, value -> new ObjectClass(ObjectClass.fromValue(value)));
    }
}