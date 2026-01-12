package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PTtlvSerializer extends AbstractKmipTtlvSerializer<P, BigInteger> {

    public PTtlvSerializer() {
        super(P::getValue);
    }
}