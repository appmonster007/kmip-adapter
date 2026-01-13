package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalXmlDeserializer() {
        super(AttributeValueInterval.kmipTag, AttributeValueInterval.encodingType, Integer.class, value -> AttributeValueInterval.builder().value(value).build());
    }
}