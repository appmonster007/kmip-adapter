package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.Y;

import java.math.BigInteger;

public class YJsonDeserializer extends AbstractKmipJsonDeserializer<Y, BigInteger> {

    public YJsonDeserializer() {
        super(Y.kmipTag, Y.encodingType, BigInteger.class, value -> Y.builder().value(value).build());
    }
}