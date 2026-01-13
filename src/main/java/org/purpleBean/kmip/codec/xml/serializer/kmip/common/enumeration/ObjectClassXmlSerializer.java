package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectClass, String> {

    public ObjectClassXmlSerializer() {
        super(ObjectClass::getDescription);
    }
}