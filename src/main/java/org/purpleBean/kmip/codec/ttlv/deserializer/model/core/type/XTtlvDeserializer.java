package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.X;

import java.math.BigInteger;

public class XTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<X, BigInteger> {

    public XTtlvDeserializer() {
        super(X.kmipTag, X.encodingType, BigInteger.class, value -> X.builder().value(value).build());
    }
}