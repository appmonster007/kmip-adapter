package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerTtlvSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}