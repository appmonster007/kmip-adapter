package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPJsonDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType, BigInteger.class, value -> PrimeExponentP.builder().value(value).build());
    }
}