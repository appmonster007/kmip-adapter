package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientXmlDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType, BigInteger.class, value -> CRTCoefficient.builder().value(value).build());
    }
}