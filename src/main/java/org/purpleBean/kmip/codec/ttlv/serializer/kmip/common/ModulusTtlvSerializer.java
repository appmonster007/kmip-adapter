package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Modulus, BigInteger> {

    public ModulusTtlvSerializer() {
        super(Modulus::getValue);
    }
}