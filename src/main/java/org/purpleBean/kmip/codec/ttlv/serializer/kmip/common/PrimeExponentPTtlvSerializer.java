package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPTtlvSerializer() {
        super(PrimeExponentP::getValue);
    }
}