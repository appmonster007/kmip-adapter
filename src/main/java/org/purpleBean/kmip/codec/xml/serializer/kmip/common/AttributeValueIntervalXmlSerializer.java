package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalXmlSerializer() {
        super(AttributeValueInterval::getValue);
    }
}