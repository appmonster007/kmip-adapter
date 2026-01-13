package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<P, BigInteger> {

    public PXmlDeserializer() {
        super(P.kmipTag, P.encodingType, BigInteger.class, value -> P.builder().value(value).build());
    }
}