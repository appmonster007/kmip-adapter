package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalTtlvSerializer() {
        super(AttributeValueInterval::getValue);
    }
}