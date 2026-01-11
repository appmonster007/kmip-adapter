package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerXmlDeserializer extends AbstractKmipXmlDeserializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType, Integer.class, value -> AttributeValueInteger.builder().value(value).build());
    }
}