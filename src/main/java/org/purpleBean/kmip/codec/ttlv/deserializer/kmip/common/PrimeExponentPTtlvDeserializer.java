package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPTtlvDeserializer extends AbstractKmipTtlvDeserializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPTtlvDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType, BigInteger.class, value -> PrimeExponentP.builder().value(value).build());
    }
}