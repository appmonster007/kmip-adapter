package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.math.BigInteger;

public class PublicExponentXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PublicExponent, BigInteger> {

    public PublicExponentXmlSerializer() {
        super(PublicExponent::getValue);
    }
}