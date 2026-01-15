package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPJsonSerializer() {
        super(PrimeExponentP::getValue);
    }
}