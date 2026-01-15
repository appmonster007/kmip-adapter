package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

public class ObjectTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectType, String> {

    public ObjectTypeJsonSerializer() {
        super(ObjectType::getDescription);
    }
}