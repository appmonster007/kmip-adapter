package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeJsonSerializer extends AbstractKmipJsonSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeJsonSerializer() {
        super(PrimeFieldSize::getValue);
    }
}