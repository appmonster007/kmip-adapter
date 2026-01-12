package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.ObjectGroup;

public class ObjectGroupTtlvDeserializer extends AbstractKmipTtlvDeserializer<ObjectGroup, String> {

    public ObjectGroupTtlvDeserializer() {
        super(ObjectGroup.kmipTag, ObjectGroup.encodingType, String.class, value -> ObjectGroup.builder().value(value).build());
    }
}