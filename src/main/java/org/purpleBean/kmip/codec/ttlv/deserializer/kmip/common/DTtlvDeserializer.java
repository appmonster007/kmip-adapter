package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<D, BigInteger> {

    public DTtlvDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}