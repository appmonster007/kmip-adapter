package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<D, BigInteger> {

    public DXmlDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}