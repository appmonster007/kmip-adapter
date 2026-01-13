package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Q, BigInteger> {

    public QJsonSerializer() {
        super(Q::getValue);
    }
}