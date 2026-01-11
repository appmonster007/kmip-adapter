package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeXmlDeserializer extends AbstractKmipXmlDeserializer<ObjectType, String> {

    public ObjectTypeXmlDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType, String.class, value -> new ObjectType(ObjectType.fromName(value)));
    }
}