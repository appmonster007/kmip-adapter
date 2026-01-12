package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQXmlSerializer extends AbstractKmipXmlSerializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQXmlSerializer() {
        super(PrimeExponentQ::getValue);
    }
}