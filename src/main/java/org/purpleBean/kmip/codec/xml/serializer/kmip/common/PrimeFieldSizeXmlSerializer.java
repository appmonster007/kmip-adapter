package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeXmlSerializer() {
        super(PrimeFieldSize::getValue);
    }
}