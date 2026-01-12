package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeName;

public class AttributeNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeName, String> {

    public AttributeNameTtlvDeserializer() {
        super(AttributeName.kmipTag, AttributeName.encodingType, String.class, value -> AttributeName.builder().value(value).build());
    }
}