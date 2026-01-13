package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrivateExponent, BigInteger> {

    public PrivateExponentXmlDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType, BigInteger.class, value -> PrivateExponent.builder().value(value).build());
    }
}