package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.PrimeExponentQ;

import java.math.BigInteger;

public class PrimeExponentQXmlDeserializer extends AbstractKmipXmlDeserializer<PrimeExponentQ, BigInteger> {

    public PrimeExponentQXmlDeserializer() {
        super(PrimeExponentQ.kmipTag, PrimeExponentQ.encodingType, BigInteger.class, value -> PrimeExponentQ.builder().value(value).build());
    }
}