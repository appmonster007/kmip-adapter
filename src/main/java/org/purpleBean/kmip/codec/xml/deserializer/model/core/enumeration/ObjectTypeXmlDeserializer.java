package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectType, String> {

    public ObjectTypeXmlDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType, String.class, value -> new ObjectType(ObjectType.fromName(value)));
    }
}