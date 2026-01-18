package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

public class AttributeValueIntervalXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalXmlDeserializer() {
        super(AttributeValueInterval.kmipTag, AttributeValueInterval.encodingType, Integer.class, value -> AttributeValueInterval.builder().value(value).build());
    }
}