package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentXmlDeserializer extends AbstractKmipXmlDeserializer<PublicExponent, BigInteger> {

    public PublicExponentXmlDeserializer() {
        super(PublicExponent.kmipTag, PublicExponent.encodingType, BigInteger.class, value -> PublicExponent.builder().value(value).build());
    }
}