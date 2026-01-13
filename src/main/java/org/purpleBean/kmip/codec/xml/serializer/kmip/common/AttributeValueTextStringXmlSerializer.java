package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringXmlSerializer() {
        super(AttributeValueTextString::getValue);
    }
}