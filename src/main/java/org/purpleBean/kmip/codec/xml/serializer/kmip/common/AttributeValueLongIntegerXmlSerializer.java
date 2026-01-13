package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueLongInteger;

public class AttributeValueLongIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerXmlSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}