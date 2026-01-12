package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientJsonSerializer extends AbstractKmipJsonSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientJsonSerializer() {
        super(CRTCoefficient::getValue);
    }
}