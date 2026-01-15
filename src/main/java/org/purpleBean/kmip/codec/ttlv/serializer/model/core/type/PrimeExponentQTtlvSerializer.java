package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQTtlvSerializer() {
        super(PrimeExponentQ::getValue);
    }
}