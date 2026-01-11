package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeIndex, Integer> {

    public AttributeIndexJsonDeserializer() {
        super(AttributeIndex.kmipTag, AttributeIndex.encodingType, Integer.class, value -> AttributeIndex.builder().value(value).build());
    }
}