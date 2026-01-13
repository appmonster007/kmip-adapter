package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlSerializer() {
        super(AttributeValueInteger::getValue);
    }
}