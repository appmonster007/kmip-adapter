package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeXmlDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType, BigInteger.class, value -> PrimeFieldSize.builder().value(value).build());
    }
}