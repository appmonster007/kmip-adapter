package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQJsonSerializer extends AbstractKmipJsonSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQJsonSerializer() {
        super(PrimeExponentQ::getValue);
    }
}