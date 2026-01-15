package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;

public class AttributeValueEnumerationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueEnumeration, Integer> {

    public AttributeValueEnumerationXmlDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType, Integer.class, value -> AttributeValueEnumeration.builder().value(value).build());
    }
}