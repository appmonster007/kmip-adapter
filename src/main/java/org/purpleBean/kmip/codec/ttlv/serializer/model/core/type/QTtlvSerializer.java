package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.Q;

import java.math.BigInteger;

public class QTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<Q, BigInteger> {

    public QTtlvSerializer() {
        super(Q::getValue);
    }
}