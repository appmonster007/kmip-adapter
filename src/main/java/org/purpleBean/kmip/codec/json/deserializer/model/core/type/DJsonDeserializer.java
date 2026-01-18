package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.D;

import java.math.BigInteger;

public class DJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<D, BigInteger> {

    public DJsonDeserializer() {
        super(D.kmipTag, D.encodingType, BigInteger.class, value -> D.builder().value(value).build());
    }
}