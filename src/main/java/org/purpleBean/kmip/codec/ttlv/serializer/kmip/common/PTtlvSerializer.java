package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<P, BigInteger> {

    public PTtlvSerializer() {
        super(P::getValue);
    }
}