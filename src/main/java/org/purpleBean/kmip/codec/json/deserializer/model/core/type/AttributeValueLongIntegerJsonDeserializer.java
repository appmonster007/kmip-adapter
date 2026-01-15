package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

public class AttributeValueLongIntegerJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerJsonDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType, Long.class, value -> AttributeValueLongInteger.builder().value(value).build());
    }
}