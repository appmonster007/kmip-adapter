package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentXmlSerializer() {
        super(PrivateExponent::getValue);
    }
}