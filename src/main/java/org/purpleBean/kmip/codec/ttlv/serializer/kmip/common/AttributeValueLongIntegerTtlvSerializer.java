package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerTtlvSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}