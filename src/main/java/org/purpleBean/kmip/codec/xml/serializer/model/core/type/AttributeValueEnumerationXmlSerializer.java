package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;

public class AttributeValueEnumerationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationXmlSerializer() {
        super(AttributeValueEnumeration::getValue);
    }
}