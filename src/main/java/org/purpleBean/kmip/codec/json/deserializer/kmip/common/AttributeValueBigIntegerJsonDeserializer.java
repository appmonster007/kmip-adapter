package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerJsonDeserializer extends AbstractKmipJsonDeserializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerJsonDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType, BigInteger.class, value -> AttributeValueBigInteger.builder().value(value).build());
    }
}