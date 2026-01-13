package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeJsonSerializer() {
        super(PrimeFieldSize::getValue);
    }
}