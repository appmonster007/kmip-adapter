package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalJsonSerializer() {
        super(AttributeValueInterval::getValue);
    }
}