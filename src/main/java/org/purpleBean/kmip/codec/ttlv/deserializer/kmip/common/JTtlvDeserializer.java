package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<J, BigInteger> {

    public JTtlvDeserializer() {
        super(J.kmipTag, J.encodingType, BigInteger.class, value -> J.builder().value(value).build());
    }
}