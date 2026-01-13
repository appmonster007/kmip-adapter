package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeIndex;

public class AttributeIndexXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeIndex, Integer> {

    public AttributeIndexXmlSerializer() {
        super(AttributeIndex::getValue);
    }
}