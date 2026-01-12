package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassJsonSerializer extends AbstractKmipJsonSerializer<ObjectClass, String> {

    public ObjectClassJsonSerializer() {
        super(ObjectClass::getDescription);
    }
}