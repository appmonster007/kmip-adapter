package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueInteger;

public class AttributeValueIntegerXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlSerializer() {
        super(AttributeValueInteger::getValue);
    }
}