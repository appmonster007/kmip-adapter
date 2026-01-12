package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerJsonSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}