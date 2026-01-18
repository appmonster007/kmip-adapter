package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientTtlvDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType, BigInteger.class, value -> CRTCoefficient.builder().value(value).build());
    }
}