package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectType, String> {

    public ObjectTypeJsonSerializer() {
        super(ObjectType::getDescription);
    }
}