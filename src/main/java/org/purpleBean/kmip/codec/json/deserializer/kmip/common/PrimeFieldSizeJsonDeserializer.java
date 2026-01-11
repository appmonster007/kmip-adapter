package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeJsonDeserializer extends AbstractKmipJsonDeserializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeJsonDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType, BigInteger.class, value -> PrimeFieldSize.builder().value(value).build());
    }
}