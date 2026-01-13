package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerJsonSerializer() {
        super(AttributeValueInteger::getValue);
    }
}