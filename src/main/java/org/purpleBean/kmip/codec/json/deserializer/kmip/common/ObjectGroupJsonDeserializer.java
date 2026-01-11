package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupJsonDeserializer extends AbstractKmipJsonDeserializer<ObjectGroup, String> {

    public ObjectGroupJsonDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}