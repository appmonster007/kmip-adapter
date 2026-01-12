package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueEnumeration;

public class AttributeValueEnumerationXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationXmlSerializer() {
        super(AttributeValueEnumeration::getValue);
    }
}