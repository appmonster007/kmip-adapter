package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusTtlvSerializer extends AbstractKmipTtlvSerializer<Modulus, BigInteger> {

    public ModulusTtlvSerializer() {
        super(Modulus::getValue);
    }
}