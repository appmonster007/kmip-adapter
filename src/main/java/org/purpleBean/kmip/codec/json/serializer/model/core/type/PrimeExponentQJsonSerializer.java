package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQJsonSerializer() {
        super(PrimeExponentQ::getValue);
    }
}