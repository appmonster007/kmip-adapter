package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.G;

import java.math.BigInteger;

public class GTtlvDeserializer extends AbstractKmipTtlvDeserializer<G, BigInteger> {

    public GTtlvDeserializer() {
        super(G.kmipTag, G.encodingType, BigInteger.class, value -> G.builder().value(value).build());
    }
}