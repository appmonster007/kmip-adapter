package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQTtlvDeserializer extends AbstractKmipTtlvDeserializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQTtlvDeserializer() {
        super(PrimeExponentQ.kmipTag, PrimeExponentQ.encodingType, BigInteger.class, value -> PrimeExponentQ.builder().value(value).build());
    }
}