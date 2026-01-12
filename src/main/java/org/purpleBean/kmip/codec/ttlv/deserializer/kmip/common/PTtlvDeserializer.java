package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PTtlvDeserializer extends AbstractKmipTtlvDeserializer<P, BigInteger> {

    public PTtlvDeserializer() {
        super(P.kmipTag, P.encodingType, BigInteger.class, value -> P.builder().value(value).build());
    }
}