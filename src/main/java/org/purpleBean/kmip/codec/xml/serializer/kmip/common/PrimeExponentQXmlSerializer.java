package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQXmlSerializer() {
        super(PrimeExponentQ::getValue);
    }
}