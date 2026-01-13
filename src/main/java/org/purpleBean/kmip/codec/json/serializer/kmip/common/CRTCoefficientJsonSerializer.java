package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientJsonSerializer() {
        super(CRTCoefficient::getValue);
    }
}