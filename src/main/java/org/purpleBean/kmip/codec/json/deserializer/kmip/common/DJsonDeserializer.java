package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<D, BigInteger> {

    public DJsonDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}