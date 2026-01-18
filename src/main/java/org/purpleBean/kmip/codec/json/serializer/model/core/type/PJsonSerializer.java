package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.P;

import java.math.BigInteger;

public class PJsonSerializer extends AbstractKmipDataTypeJsonSerializer<P, BigInteger> {

    public PJsonSerializer() {
        super(P::getValue);
    }
}