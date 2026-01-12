package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerTtlvDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType, Long.class, value -> AttributeValueLongInteger.builder().value(value).build());
    }
}