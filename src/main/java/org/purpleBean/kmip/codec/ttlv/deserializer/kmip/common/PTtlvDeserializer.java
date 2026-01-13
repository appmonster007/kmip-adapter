package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<P, BigInteger> {

    public PTtlvDeserializer() {
        super(P.kmipTag, P.encodingType, BigInteger.class, value -> P.builder().value(value).build());
    }
}