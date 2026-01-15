package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientTtlvSerializer() {
        super(CRTCoefficient::getValue);
    }
}