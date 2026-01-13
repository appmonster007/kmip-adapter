package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQJsonSerializer() {
        super(PrimeExponentQ::getValue);
    }
}