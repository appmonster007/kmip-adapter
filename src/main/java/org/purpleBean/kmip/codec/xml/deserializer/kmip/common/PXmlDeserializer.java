package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PXmlDeserializer extends AbstractKmipXmlDeserializer<P, BigInteger> {

    public PXmlDeserializer() {
        super(P.kmipTag, P.encodingType, BigInteger.class, value -> P.builder().value(value).build());
    }
}