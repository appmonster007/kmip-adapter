package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.P;

import java.math.BigInteger;

public class PJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<P, BigInteger> {

    public PJsonDeserializer() {
        super(P.kmipTag, P.encodingType, BigInteger.class, value -> P.builder().value(value).build());
    }
}