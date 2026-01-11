package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeJsonDeserializer extends AbstractKmipJsonDeserializer<ObjectType, String> {

    public ObjectTypeJsonDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType, String.class, value -> new ObjectType(ObjectType.fromName(value)));
    }
}