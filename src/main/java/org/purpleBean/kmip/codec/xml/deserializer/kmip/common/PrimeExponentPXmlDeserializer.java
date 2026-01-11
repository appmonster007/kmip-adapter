package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPXmlDeserializer extends AbstractKmipXmlDeserializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPXmlDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType, BigInteger.class, value -> PrimeExponentP.builder().value(value).build());
    }
}