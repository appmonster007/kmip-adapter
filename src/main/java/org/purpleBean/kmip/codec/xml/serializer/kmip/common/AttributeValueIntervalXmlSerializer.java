package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AttributeValueInterval;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttributeValueIntervalXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalXmlSerializer() {
        super(AttributeValueInterval::getValue);
    }
}