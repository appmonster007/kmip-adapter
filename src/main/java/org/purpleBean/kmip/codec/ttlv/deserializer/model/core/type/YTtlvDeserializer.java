package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class YTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Y, BigInteger> {

    public YTtlvDeserializer() {
        super(Y.kmipTag, Y.encodingType, BigInteger.class, value -> Y.builder().value(value).build());
    }
}