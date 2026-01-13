package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CRTCoefficient;

import java.math.BigInteger;

public class CRTCoefficientXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CRTCoefficient, BigInteger> {

    public CRTCoefficientXmlSerializer() {
        super(CRTCoefficient::getValue);
    }
}