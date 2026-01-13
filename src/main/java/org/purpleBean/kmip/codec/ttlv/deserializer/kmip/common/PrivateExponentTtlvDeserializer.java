package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrivateExponent, BigInteger> {

    public PrivateExponentTtlvDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType, BigInteger.class, value -> PrivateExponent.builder().value(value).build());
    }
}