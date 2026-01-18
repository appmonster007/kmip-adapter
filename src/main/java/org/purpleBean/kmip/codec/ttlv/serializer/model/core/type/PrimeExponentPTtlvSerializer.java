package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPTtlvSerializer() {
        super(PrimeExponentP::getValue);
    }
}