package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPJsonSerializer extends AbstractKmipJsonSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPJsonSerializer() {
        super(PrimeExponentP::getValue);
    }
}