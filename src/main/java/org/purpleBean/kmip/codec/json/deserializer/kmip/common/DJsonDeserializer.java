package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DJsonDeserializer extends AbstractKmipJsonDeserializer<D, BigInteger> {

    public DJsonDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}