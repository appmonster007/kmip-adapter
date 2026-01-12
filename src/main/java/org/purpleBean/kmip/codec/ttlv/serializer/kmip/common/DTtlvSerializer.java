package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.D;

import java.math.BigInteger;

public class DTtlvSerializer extends AbstractKmipTtlvSerializer<D, BigInteger> {

    public DTtlvSerializer() {
        super(D::getValue);
    }
}