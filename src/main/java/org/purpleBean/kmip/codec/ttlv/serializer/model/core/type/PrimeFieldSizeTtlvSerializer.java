package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeTtlvSerializer() {
        super(PrimeFieldSize::getValue);
    }
}