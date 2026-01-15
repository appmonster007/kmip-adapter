package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.ObjectGroup;

public class ObjectGroupTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ObjectGroup, String> {

    public ObjectGroupTtlvDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}