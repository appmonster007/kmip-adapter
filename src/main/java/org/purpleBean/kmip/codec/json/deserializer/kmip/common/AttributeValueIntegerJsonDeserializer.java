package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerJsonDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType, Integer.class, value -> AttributeValueInteger.builder().value(value).build());
    }
}