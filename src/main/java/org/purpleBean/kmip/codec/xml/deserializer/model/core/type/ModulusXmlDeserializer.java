package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.math.BigInteger;

public class ModulusXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Modulus, BigInteger> {

    public ModulusXmlDeserializer() {
        super(Modulus.kmipTag, Modulus.encodingType, BigInteger.class, value -> Modulus.builder().value(value).build());
    }
}