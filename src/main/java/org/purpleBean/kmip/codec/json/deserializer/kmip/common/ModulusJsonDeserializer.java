package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Modulus, BigInteger> {

    public ModulusJsonDeserializer() {
        super(Modulus.kmipTag, Modulus.encodingType, BigInteger.class, value -> Modulus.builder().value(value).build());
    }
}