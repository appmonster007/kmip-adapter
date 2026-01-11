package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalJsonDeserializer() {
        super(AttributeValueInterval.kmipTag, AttributeValueInterval.encodingType, Integer.class, value -> AttributeValueInterval.builder().value(value).build());
    }
}