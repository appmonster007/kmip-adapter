package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JJsonDeserializer extends AbstractKmipJsonDeserializer<J, BigInteger> {

    public JJsonDeserializer() {
        super(J.kmipTag, J.encodingType, BigInteger.class, value -> J.builder().value(value).build());
    }
}