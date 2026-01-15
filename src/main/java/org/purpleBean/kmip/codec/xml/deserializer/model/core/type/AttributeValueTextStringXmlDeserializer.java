package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;

public class AttributeValueTextStringXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueTextString, String> {

    public AttributeValueTextStringXmlDeserializer() {
        super(AttributeValueTextString.kmipTag, AttributeValueTextString.encodingType, String.class, value -> AttributeValueTextString.builder().value(value).build());
    }
}