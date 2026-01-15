package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;

public class AttributeValueEnumerationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationTtlvDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, value -> AttributeValueEnumeration.builder().value(value).build());
    }
}