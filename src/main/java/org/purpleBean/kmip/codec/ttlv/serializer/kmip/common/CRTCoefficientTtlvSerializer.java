package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientTtlvSerializer extends AbstractKmipTtlvSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientTtlvSerializer() {
        super(CRTCoefficient::getValue);
    }
}