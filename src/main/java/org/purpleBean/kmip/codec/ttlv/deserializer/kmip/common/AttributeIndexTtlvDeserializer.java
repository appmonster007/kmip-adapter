package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeIndex, Integer> {

    public AttributeIndexTtlvDeserializer() {
        super(AttributeIndex.kmipTag, AttributeIndex.encodingType, Integer.class, value -> AttributeIndex.builder().value(value).build());
    }
}