package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQJsonDeserializer extends AbstractKmipJsonDeserializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQJsonDeserializer() {
        super(PrimeExponentQ.kmipTag, PrimeExponentQ.encodingType, BigInteger.class, value -> PrimeExponentQ.builder().value(value).build());
    }
}