package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeXmlSerializer extends AbstractKmipXmlSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeXmlSerializer() {
        super(PrimeFieldSize::getValue);
    }
}