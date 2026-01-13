package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PJsonSerializer extends AbstractKmipDataTypeJsonSerializer<P, BigInteger> {

    public PJsonSerializer() {
        super(P::getValue);
    }
}