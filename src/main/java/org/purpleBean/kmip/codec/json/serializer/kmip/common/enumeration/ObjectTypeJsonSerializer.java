package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectType;

public class ObjectTypeJsonSerializer extends AbstractKmipJsonSerializer<ObjectType, String> {

    public ObjectTypeJsonSerializer() {
        super(ObjectType::getDescription);
    }
}