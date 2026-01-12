package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentXmlSerializer extends AbstractKmipXmlSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentXmlSerializer() {
        super(PrivateExponent::getValue);
    }
}