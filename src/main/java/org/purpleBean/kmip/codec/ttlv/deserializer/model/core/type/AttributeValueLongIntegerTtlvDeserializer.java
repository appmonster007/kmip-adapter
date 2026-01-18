package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

public class AttributeValueLongIntegerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerTtlvDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType, Long.class, value -> AttributeValueLongInteger.builder().value(value).build());
    }
}