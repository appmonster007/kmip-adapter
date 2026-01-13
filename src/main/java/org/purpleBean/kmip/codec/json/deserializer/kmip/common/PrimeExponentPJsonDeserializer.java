package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPJsonDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType, BigInteger.class, value -> PrimeExponentP.builder().value(value).build());
    }
}