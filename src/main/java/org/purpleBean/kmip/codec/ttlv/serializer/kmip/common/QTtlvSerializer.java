package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Q, BigInteger> {

    public QTtlvSerializer() {
        super(Q::getValue);
    }
}