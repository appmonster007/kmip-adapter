package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Y;

import java.math.BigInteger;

public class YJsonSerializer extends AbstractKmipJsonSerializer<Y, BigInteger> {

    public YJsonSerializer() {
        super(Y::getValue);
    }
}