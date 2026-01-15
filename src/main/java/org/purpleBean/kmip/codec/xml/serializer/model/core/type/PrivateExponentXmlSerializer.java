package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentXmlSerializer() {
        super(PrivateExponent::getValue);
    }
}