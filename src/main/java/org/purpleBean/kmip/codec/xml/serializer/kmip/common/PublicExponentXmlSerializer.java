package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PublicExponent, BigInteger> {

    public PublicExponentXmlSerializer() {
        super(PublicExponent::getValue);
    }
}