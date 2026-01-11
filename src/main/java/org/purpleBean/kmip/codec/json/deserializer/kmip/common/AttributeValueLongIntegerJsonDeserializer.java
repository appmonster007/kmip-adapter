package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerJsonDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType, Long.class, value -> AttributeValueLongInteger.builder().value(value).build());
    }
}