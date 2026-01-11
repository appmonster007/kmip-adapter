package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentJsonDeserializer extends AbstractKmipJsonDeserializer<PrivateExponent, BigInteger> {

    public PrivateExponentJsonDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType, BigInteger.class, value -> PrivateExponent.builder().value(value).build());
    }
}