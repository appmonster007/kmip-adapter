package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientTtlvSerializer() {
        super(CRTCoefficient::getValue);
    }
}