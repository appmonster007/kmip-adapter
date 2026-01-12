package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanTtlvDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType, Boolean.class, value -> AttributeValueBoolean.builder().value(value).build());
    }
}