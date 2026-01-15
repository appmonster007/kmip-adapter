package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Q;

import java.math.BigInteger;

public class QJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Q, BigInteger> {

    public QJsonSerializer() {
        super(Q::getValue);
    }
}