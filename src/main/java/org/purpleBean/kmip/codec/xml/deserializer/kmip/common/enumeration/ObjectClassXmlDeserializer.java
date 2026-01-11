package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassXmlDeserializer extends AbstractKmipXmlDeserializer<ObjectClass, String> {

    public ObjectClassXmlDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType, String.class, value -> new ObjectClass(ObjectClass.fromName(value)));
    }
}