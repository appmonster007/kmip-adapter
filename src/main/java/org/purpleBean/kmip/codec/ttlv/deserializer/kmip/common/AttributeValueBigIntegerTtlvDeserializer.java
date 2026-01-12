package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerTtlvDeserializer extends AbstractKmipTtlvDeserializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerTtlvDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType, BigInteger.class, value -> AttributeValueBigInteger.builder().value(value).build());
    }
}