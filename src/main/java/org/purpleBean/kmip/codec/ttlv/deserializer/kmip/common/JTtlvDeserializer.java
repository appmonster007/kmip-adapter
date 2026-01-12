package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JTtlvDeserializer extends AbstractKmipTtlvDeserializer<J, BigInteger> {

    public JTtlvDeserializer() {
        super(J.kmipTag, J.encodingType, BigInteger.class, value -> J.builder().value(value).build());
    }
}