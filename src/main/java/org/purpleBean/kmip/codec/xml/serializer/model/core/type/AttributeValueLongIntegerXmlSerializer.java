package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;

public class AttributeValueLongIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueLongInteger, Long> {

    public AttributeValueLongIntegerXmlSerializer() {
        super(AttributeValueLongInteger::getValue);
    }
}