package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentTtlvDeserializer extends AbstractKmipTtlvDeserializer<PublicExponent, BigInteger> {

    public PublicExponentTtlvDeserializer() {
        super(PublicExponent.kmipTag, PublicExponent.encodingType, BigInteger.class, value -> PublicExponent.builder().value(value).build());
    }
}