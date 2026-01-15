package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPTtlvDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType, BigInteger.class, value -> PrimeExponentP.builder().value(value).build());
    }
}