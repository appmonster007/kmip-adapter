package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientXmlDeserializer extends AbstractKmipXmlDeserializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientXmlDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType, BigInteger.class, value -> CRTCoefficient.builder().value(value).build());
    }
}