package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringXmlDeserializer extends AbstractKmipXmlDeserializer<AttributeValueTextString, String> {

    public AttributeValueTextStringXmlDeserializer() {
        super(AttributeValueTextString.kmipTag, AttributeValueTextString.encodingType, String.class, value -> AttributeValueTextString.builder().value(value).build());
    }
}