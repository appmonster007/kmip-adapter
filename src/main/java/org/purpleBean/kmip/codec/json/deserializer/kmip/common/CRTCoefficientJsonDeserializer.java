package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientJsonDeserializer extends AbstractKmipJsonDeserializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientJsonDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType, BigInteger.class, value -> CRTCoefficient.builder().value(value).build());
    }
}