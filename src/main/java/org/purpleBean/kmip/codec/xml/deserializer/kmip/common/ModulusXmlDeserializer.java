package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Modulus, BigInteger> {

    public ModulusXmlDeserializer() {
        super(Modulus.kmipTag, Modulus.encodingType, BigInteger.class, value -> Modulus.builder().value(value).build());
    }
}