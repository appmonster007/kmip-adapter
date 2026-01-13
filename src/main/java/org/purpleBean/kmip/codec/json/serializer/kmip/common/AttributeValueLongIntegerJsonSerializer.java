package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerJsonSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}