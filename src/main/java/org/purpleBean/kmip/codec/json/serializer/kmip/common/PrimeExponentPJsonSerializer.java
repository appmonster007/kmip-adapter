package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPJsonSerializer() {
        super(PrimeExponentP::getValue);
    }
}