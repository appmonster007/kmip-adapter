package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<X, BigInteger> {

    public XJsonDeserializer() {
        super(X.kmipTag, X.encodingType, BigInteger.class, value -> X.builder().value(value).build());
    }
}