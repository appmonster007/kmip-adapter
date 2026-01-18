package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.math.BigInteger;

public class ModulusTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Modulus, BigInteger> {

    public ModulusTtlvSerializer() {
        super(Modulus::getValue);
    }
}