package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.G;

import java.math.BigInteger;

public class GXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<G, BigInteger> {

    public GXmlDeserializer() {
        super(G.kmipTag, G.encodingType, BigInteger.class, value -> G.builder().value(value).build());
    }
}