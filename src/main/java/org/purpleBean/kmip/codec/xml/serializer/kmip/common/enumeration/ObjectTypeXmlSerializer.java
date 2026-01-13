package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectType, String> {

    public ObjectTypeXmlSerializer() {
        super(ObjectType::getDescription);
    }
}