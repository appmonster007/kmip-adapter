package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeName, String> {

    public AttributeNameXmlSerializer() {
        super(AttributeName::getValue);
    }
}