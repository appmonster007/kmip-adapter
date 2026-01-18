package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<ObjectType, String> {

    public ObjectTypeXmlSerializer() {
        super(ObjectType::getDescription);
    }
}