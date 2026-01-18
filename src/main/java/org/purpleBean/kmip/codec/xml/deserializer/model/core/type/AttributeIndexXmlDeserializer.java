package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeIndex;

public class AttributeIndexXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeIndex, Integer> {

    public AttributeIndexXmlDeserializer() {
        super(AttributeIndex.kmipTag, AttributeIndex.encodingType, Integer.class, value -> AttributeIndex.builder().value(value).build());
    }
}