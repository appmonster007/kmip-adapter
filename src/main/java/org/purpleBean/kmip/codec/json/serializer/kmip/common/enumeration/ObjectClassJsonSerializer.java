package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.ObjectClass;

public class ObjectClassJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectClass, String> {

    public ObjectClassJsonSerializer() {
        super(ObjectClass::getDescription);
    }
}