package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<PrivateExponent, BigInteger> {

    public PrivateExponentXmlDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType, BigInteger.class, value -> PrivateExponent.builder().value(value).build());
    }
}