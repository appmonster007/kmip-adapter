package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectClass, String> {

    public ObjectClassXmlSerializer() {
        super(ObjectClass::getDescription);
    }
}