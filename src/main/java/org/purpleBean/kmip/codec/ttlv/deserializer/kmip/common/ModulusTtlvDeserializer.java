package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusTtlvDeserializer extends AbstractKmipTtlvDeserializer<Modulus, BigInteger> {

    public ModulusTtlvDeserializer() {
        super(Modulus.kmipTag, Modulus.encodingType, BigInteger.class, value -> Modulus.builder().value(value).build());
    }
}