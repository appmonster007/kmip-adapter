package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DXmlDeserializer extends AbstractKmipXmlDeserializer<D, BigInteger> {

    public DXmlDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}