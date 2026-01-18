package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.math.BigInteger;

public class ModulusTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Modulus, BigInteger> {

    public ModulusTtlvDeserializer() {
        super(Modulus.kmipTag, Modulus.encodingType, BigInteger.class, value -> Modulus.builder().value(value).build());
    }
}