package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringXmlSerializer() {
        super(AttributeValueTextString::getValue);
    }
}