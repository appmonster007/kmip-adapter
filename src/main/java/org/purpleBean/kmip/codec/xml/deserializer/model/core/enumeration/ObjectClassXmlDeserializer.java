package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectClass, String> {

    public ObjectClassXmlDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType, String.class, value -> ObjectClass.fromName(value).inst());
    }
}