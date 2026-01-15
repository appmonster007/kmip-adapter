package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeXmlDeserializer() {
        super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType, BigInteger.class, value -> PrimeFieldSize.builder().value(value).build());
    }
}