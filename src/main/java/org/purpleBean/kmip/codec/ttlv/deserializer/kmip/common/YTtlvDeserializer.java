package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.Y;

import java.math.BigInteger;

public class YTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Y, BigInteger> {

    public YTtlvDeserializer() {
        super(Y.kmipTag, Y.encodingType, BigInteger.class, value -> Y.builder().value(value).build());
    }
}