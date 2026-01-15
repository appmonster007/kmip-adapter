package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Modulus;

import java.math.BigInteger;

public class ModulusJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Modulus, BigInteger> {

    public ModulusJsonSerializer() {
        super(Modulus::getValue);
    }
}