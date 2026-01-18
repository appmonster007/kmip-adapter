package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerTtlvDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType, BigInteger.class, value -> AttributeValueBigInteger.builder().value(value).build());
    }
}