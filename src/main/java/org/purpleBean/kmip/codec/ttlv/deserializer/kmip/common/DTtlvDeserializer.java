package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DTtlvDeserializer extends AbstractKmipTtlvDeserializer<D, BigInteger> {

    public DTtlvDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}