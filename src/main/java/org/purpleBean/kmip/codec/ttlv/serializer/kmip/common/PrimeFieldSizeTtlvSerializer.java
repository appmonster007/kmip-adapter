package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeTtlvSerializer() {
        super(PrimeFieldSize::getValue);
    }
}