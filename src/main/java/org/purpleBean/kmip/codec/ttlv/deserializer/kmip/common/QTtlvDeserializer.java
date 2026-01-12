package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QTtlvDeserializer extends AbstractKmipTtlvDeserializer<Q, BigInteger> {

    public QTtlvDeserializer() {
        super(Q.kmipTag, Q.encodingType, BigInteger.class, value -> Q.builder().value(value).build());
    }
}