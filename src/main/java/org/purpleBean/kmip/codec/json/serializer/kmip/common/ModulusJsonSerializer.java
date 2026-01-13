package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Modulus;

import java.math.BigInteger;

public class ModulusJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Modulus, BigInteger> {

    public ModulusJsonSerializer() {
        super(Modulus::getValue);
    }
}