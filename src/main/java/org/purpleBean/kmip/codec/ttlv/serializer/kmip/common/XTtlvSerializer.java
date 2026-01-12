package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.X;

import java.math.BigInteger;

public class XTtlvSerializer extends AbstractKmipTtlvSerializer<X, BigInteger> {

    public XTtlvSerializer() {
        super(X::getValue);
    }
}