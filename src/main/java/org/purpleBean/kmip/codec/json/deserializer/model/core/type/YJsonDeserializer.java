package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.Y;

import java.math.BigInteger;

public class YJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Y, BigInteger> {

    public YJsonDeserializer() {
        super(Y.kmipTag, Y.encodingType, BigInteger.class, value -> Y.builder().value(value).build());
    }
}