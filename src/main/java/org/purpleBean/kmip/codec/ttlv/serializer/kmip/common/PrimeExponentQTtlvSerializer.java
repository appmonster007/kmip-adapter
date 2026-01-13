package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQTtlvSerializer() {
        super(PrimeExponentQ::getValue);
    }
}