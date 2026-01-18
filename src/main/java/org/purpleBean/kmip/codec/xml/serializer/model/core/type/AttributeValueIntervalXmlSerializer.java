package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

public class AttributeValueIntervalXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalXmlSerializer() {
        super(AttributeValueInterval::getValue);
    }
}