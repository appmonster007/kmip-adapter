package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassJsonDeserializer extends AbstractKmipJsonDeserializer<ObjectClass, String> {

    public ObjectClassJsonDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType, String.class, value -> new ObjectClass(ObjectClass.fromName(value)));
    }
}