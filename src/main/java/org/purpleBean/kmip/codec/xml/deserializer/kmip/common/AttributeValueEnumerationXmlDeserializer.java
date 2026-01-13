package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationXmlDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, value -> AttributeValueEnumeration.builder().value(value).build());
    }
}