package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeName, String> {

    public AttributeNameJsonDeserializer() {
        super(AttributeName.kmipTag, AttributeName.encodingType, String.class, value -> AttributeName.builder().value(value).build());
    }
}