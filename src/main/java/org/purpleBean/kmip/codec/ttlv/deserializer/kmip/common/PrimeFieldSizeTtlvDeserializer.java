package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeTtlvDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType, BigInteger.class, value -> PrimeFieldSize.builder().value(value).build());
    }
}