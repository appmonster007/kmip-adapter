package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueTextString, String> {

    public AttributeValueTextStringJsonDeserializer() {
        super(AttributeValueTextString.kmipTag, AttributeValueTextString.encodingType, String.class, value -> AttributeValueTextString.builder().value(value).build());
    }
}