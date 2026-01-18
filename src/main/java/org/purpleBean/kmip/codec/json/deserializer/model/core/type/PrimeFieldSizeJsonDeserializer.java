package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeJsonDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType, BigInteger.class, value -> PrimeFieldSize.builder().value(value).build());
    }
}