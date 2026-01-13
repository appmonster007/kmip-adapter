package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Q, BigInteger> {

    public QJsonDeserializer() {
        super(Q.kmipTag, Q.encodingType, BigInteger.class, value -> Q.builder().value(value).build());
    }
}