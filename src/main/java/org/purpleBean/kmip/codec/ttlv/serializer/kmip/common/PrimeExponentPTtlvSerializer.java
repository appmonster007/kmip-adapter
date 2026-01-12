package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPTtlvSerializer extends AbstractKmipTtlvSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPTtlvSerializer() {
        super(PrimeExponentP::getValue);
    }
}