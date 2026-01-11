package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XXmlDeserializer extends AbstractKmipXmlDeserializer<X, BigInteger> {

    public XXmlDeserializer() {
        super(X.kmipTag, X.encodingType, BigInteger.class, value -> X.builder().value(value).build());
    }
}