package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.Q;

import java.math.BigInteger;

public class QTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Q, BigInteger> {

    public QTtlvDeserializer() {
        super(Q.kmipTag, Q.encodingType, BigInteger.class, value -> Q.builder().value(value).build());
    }
}