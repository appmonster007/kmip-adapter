package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.PublicExponent;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class PublicExponentXmlSerializer extends AbstractKmipXmlSerializer<PublicExponent, BigInteger> {

    public PublicExponentXmlSerializer() {
        super(PublicExponent::getValue);
    }
}