package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QJsonSerializer extends AbstractKmipJsonSerializer<Q, BigInteger> {

    public QJsonSerializer() {
        super(Q::getValue);
    }
}