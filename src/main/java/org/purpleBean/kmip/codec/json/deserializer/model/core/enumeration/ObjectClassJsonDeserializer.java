package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectClass, String> {

    public ObjectClassJsonDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType, String.class, value -> new ObjectClass(ObjectClass.fromName(value)));
    }
}