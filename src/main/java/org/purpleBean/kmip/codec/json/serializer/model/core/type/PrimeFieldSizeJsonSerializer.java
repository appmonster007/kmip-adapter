package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;

import java.math.BigInteger;

public class PrimeFieldSizeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrimeFieldSize, BigInteger> {

    public PrimeFieldSizeJsonSerializer() {
        super(PrimeFieldSize::getValue);
    }
}