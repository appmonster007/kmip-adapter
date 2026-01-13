package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectGroup, String> {

    public ObjectGroupJsonDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}