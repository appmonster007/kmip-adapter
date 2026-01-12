package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XTtlvDeserializer extends AbstractKmipTtlvDeserializer<X, BigInteger> {

    public XTtlvDeserializer() {
        super(X.kmipTag, X.encodingType, BigInteger.class, value -> X.builder().value(value).build());
    }
}