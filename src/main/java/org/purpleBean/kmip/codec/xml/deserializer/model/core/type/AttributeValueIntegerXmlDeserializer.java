package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class AttributeValueIntegerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType, Integer.class, value -> AttributeValueInteger.builder().value(value).build());
    }
}