package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XJsonDeserializer extends AbstractKmipJsonDeserializer<X, BigInteger> {

    public XJsonDeserializer() {
        super(X.kmipTag, X.encodingType, BigInteger.class, value -> X.builder().value(value).build());
    }
}