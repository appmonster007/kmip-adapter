package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AttributeValueLongInteger;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttributeValueLongIntegerXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerXmlSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}