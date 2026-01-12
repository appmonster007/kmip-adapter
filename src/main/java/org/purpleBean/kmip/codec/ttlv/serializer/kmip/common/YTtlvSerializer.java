package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Y;

import java.math.BigInteger;

public class YTtlvSerializer extends AbstractKmipTtlvSerializer<Y, BigInteger> {

    public YTtlvSerializer() {
        super(Y::getValue);
    }
}