package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.J;

import java.math.BigInteger;

public class JXmlDeserializer extends AbstractKmipXmlDeserializer<J, BigInteger> {

    public JXmlDeserializer() {
        super(J.kmipTag, J.encodingType, BigInteger.class, value -> J.builder().value(value).build());
    }
}