package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Q, BigInteger> {

    public QXmlDeserializer() {
        super(Q.kmipTag, Q.encodingType, BigInteger.class, value -> Q.builder().value(value).build());
    }
}