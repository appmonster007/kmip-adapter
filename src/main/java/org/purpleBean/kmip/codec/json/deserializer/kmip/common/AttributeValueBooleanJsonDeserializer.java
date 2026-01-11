package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanJsonDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType, Boolean.class, value -> AttributeValueBoolean.builder().value(value).build());
    }
}