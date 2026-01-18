package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectGroup, String> {

    public ObjectGroupJsonDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}