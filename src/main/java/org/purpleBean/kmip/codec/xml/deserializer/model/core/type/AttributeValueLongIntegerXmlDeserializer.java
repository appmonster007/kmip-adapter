package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

public class AttributeValueLongIntegerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerXmlDeserializer() {
        super(AttributeValueLongInteger.kmipTag, AttributeValueLongInteger.encodingType, Long.class, value -> AttributeValueLongInteger.builder().value(value).build());
    }
}