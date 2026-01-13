package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPXmlDeserializer() {
        super(PrimeExponentP.kmipTag, PrimeExponentP.encodingType, BigInteger.class, value -> PrimeExponentP.builder().value(value).build());
    }
}