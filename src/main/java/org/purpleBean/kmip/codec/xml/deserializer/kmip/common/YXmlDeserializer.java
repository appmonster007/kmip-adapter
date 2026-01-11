package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Y;

import java.math.BigInteger;

public class YXmlDeserializer extends AbstractKmipXmlDeserializer<Y, BigInteger> {

    public YXmlDeserializer() {
        super(Y.kmipTag, Y.encodingType, BigInteger.class, value -> Y.builder().value(value).build());
    }
}