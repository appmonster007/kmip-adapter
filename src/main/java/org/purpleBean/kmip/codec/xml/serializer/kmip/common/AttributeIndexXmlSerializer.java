package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class AttributeIndexXmlSerializer extends AbstractKmipXmlSerializer<AttributeIndex, Integer> {

    public AttributeIndexXmlSerializer() {
        super(AttributeIndex::getValue);
    }
}