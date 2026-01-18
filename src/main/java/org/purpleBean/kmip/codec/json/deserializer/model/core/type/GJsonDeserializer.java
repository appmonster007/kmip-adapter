package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.G;

import java.math.BigInteger;

public class GJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<G, BigInteger> {

    public GJsonDeserializer() {
        super(G.kmipTag, G.encodingType, BigInteger.class, value -> G.builder().value(value).build());
    }
}