package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.math.BigInteger;

public class PublicExponentTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PublicExponent, BigInteger> {

    public PublicExponentTtlvDeserializer() {
        super(PublicExponent.kmipTag, PublicExponent.encodingType, BigInteger.class, value -> PublicExponent.builder().value(value).build());
    }
}