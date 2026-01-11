package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentXmlDeserializer extends AbstractKmipXmlDeserializer<PrivateExponent, BigInteger> {

    public PrivateExponentXmlDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType, BigInteger.class, value -> PrivateExponent.builder().value(value).build());
    }
}