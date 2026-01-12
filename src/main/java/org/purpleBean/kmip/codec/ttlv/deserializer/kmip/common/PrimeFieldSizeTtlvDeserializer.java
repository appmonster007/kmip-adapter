package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeTtlvDeserializer extends AbstractKmipTtlvDeserializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeTtlvDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType, BigInteger.class, value -> PrimeFieldSize.builder().value(value).build());
    }
}