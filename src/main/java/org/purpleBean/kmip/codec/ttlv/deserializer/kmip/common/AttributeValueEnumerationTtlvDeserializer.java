package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationTtlvDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, value -> AttributeValueEnumeration.builder().value(value).build());
    }
}