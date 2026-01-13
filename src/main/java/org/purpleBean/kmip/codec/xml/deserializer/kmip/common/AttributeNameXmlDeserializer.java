package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeName, String> {

    public AttributeNameXmlDeserializer() {
        super(AttributeName.kmipTag, AttributeName.encodingType, String.class, value -> AttributeName.builder().value(value).build());
    }
}