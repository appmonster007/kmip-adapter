package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexXmlDeserializer extends AbstractKmipXmlDeserializer<AttributeIndex, Integer> {

    public AttributeIndexXmlDeserializer() {
        super(AttributeIndex.kmipTag, AttributeIndex.encodingType, Integer.class, value -> AttributeIndex.builder().value(value).build());
    }
}