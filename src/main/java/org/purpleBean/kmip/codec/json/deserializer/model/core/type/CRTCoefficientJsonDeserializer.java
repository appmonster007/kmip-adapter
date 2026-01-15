package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientJsonDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType, BigInteger.class, value -> CRTCoefficient.builder().value(value).build());
    }
}