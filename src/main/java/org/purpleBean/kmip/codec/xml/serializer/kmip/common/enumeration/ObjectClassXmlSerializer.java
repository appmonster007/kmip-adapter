package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ObjectClass;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ObjectClassXmlSerializer extends AbstractKmipXmlSerializer<ObjectClass, String> {

    public ObjectClassXmlSerializer() {
        super(ObjectClass::getDescription);
    }
}