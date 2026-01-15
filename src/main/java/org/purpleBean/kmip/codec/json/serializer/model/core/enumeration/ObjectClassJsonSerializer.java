package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

public class ObjectClassJsonSerializer extends AbstractKmipDataTypeJsonSerializer<ObjectClass, String> {

    public ObjectClassJsonSerializer() {
        super(ObjectClass::getDescription);
    }
}