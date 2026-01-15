package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Q;

import java.math.BigInteger;

public class QJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Q, BigInteger> {

    public QJsonDeserializer() {
        super(Q.kmipTag, Q.encodingType, BigInteger.class, value -> Q.builder().value(value).build());
    }
}