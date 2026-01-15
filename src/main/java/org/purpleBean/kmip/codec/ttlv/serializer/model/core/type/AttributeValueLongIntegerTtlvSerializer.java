package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

public class AttributeValueLongIntegerTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerTtlvSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}