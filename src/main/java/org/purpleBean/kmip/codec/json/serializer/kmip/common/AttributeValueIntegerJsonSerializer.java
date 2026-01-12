package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerJsonSerializer() {
        super(AttributeValueInteger::getValue);
    }
}