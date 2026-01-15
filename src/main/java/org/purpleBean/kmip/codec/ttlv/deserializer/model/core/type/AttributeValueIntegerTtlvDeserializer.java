package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class AttributeValueIntegerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerTtlvDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType, Integer.class, value -> AttributeValueInteger.builder().value(value).build());
    }
}