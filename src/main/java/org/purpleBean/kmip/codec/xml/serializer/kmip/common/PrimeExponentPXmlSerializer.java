package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPXmlSerializer() {
        super(PrimeExponentP::getValue);
    }
}