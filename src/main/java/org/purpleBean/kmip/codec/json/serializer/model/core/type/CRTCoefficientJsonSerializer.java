package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientJsonSerializer extends AbstractKmipDataTypeJsonSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientJsonSerializer() {
        super(CRTCoefficient::getValue);
    }
}