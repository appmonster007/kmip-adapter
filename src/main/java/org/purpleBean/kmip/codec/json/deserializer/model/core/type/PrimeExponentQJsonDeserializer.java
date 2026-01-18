package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQJsonDeserializer() {
        super(PrimeExponentQ.kmipTag, PrimeExponentQ.encodingType, BigInteger.class, value -> PrimeExponentQ.builder().value(value).build());
    }
}