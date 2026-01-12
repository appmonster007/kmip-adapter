package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusJsonSerializer extends AbstractKmipJsonSerializer<Modulus, BigInteger> {

    public ModulusJsonSerializer() {
        super(Modulus::getValue);
    }
}