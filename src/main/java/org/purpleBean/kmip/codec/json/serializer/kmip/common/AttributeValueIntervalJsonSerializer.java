package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalJsonSerializer() {
        super(AttributeValueInterval::getValue);
    }
}