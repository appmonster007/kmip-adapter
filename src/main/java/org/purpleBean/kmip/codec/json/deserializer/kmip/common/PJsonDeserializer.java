package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<P, BigInteger> {

    public PJsonDeserializer() {
        super(P.kmipTag, P.encodingType, BigInteger.class, value -> P.builder().value(value).build());
    }
}