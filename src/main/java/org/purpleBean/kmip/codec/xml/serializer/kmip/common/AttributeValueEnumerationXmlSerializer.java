package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationXmlSerializer() {
        super(AttributeValueEnumeration::getValue);
    }
}