package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectType, String> {

    public ObjectTypeJsonDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType, String.class, value -> new ObjectType(ObjectType.fromName(value)));
    }
}