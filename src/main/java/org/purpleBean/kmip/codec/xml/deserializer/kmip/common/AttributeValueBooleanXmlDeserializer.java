package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanXmlDeserializer() {
        super(AttributeValueBoolean.kmipTag, AttributeValueBoolean.encodingType, Boolean.class, value -> AttributeValueBoolean.builder().value(value).build());
    }
}