package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.ObjectType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class ObjectTypeXmlSerializer extends AbstractKmipXmlSerializer<ObjectType, String> {

    public ObjectTypeXmlSerializer() {
        super(ObjectType::getDescription);
    }
}