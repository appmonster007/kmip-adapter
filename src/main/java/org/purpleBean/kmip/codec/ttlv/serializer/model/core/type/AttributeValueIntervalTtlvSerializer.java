package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

public class AttributeValueIntervalTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalTtlvSerializer() {
        super(AttributeValueInterval::getValue);
    }
}