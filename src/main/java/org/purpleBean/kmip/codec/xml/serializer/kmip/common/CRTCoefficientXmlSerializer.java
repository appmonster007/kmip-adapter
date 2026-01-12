package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.CRTCoefficient;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class CRTCoefficientXmlSerializer extends AbstractKmipXmlSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientXmlSerializer() {
        super(CRTCoefficient::getValue);
    }
}