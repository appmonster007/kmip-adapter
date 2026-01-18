package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

public class AttributeValueIntervalJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalJsonDeserializer() {
        super(AttributeValueInterval.kmipTag, AttributeValueInterval.encodingType, Integer.class, value -> AttributeValueInterval.builder().value(value).build());
    }
}