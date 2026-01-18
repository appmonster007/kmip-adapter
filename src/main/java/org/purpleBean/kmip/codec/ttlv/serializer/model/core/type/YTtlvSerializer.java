package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class YTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Y, BigInteger> {

    public YTtlvSerializer() {
        super(Y::getValue);
    }
}