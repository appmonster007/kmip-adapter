package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttributeValueTextStringXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringXmlSerializer() {
        super(AttributeValueTextString::getValue);
    }
}