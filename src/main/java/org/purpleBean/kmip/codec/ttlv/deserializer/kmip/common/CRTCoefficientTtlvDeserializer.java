package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientTtlvDeserializer extends AbstractKmipTtlvDeserializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientTtlvDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType, BigInteger.class, value -> CRTCoefficient.builder().value(value).build());
    }
}