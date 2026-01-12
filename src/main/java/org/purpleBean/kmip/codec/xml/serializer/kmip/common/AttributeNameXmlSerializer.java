package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttributeNameXmlSerializer extends AbstractKmipXmlSerializer<AttributeName, String> {

    public AttributeNameXmlSerializer() {
        super(AttributeName::getValue);
    }
}