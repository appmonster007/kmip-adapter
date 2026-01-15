package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;

public class AttributeValueIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueInteger, Integer> {

    public AttributeValueIntegerXmlSerializer() {
        super(AttributeValueInteger::getValue);
    }
}