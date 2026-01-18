package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.math.BigInteger;

public class PublicExponentXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PublicExponent, BigInteger> {

    public PublicExponentXmlDeserializer() {
        super(PublicExponent.kmipTag, PublicExponent.encodingType, BigInteger.class, value -> PublicExponent.builder().value(value).build());
    }
}