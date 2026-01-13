package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.G;

import java.math.BigInteger;

public class GJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<G, BigInteger> {

    public GJsonDeserializer() {
        super(G.kmipTag, G.encodingType, BigInteger.class, value -> G.builder().value(value).build());
    }
}