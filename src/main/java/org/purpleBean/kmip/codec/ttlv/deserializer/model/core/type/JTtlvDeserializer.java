package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.J;

import java.math.BigInteger;

public class JTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<J, BigInteger> {

    public JTtlvDeserializer() {
        super(J.kmipTag, J.encodingType, BigInteger.class, value -> J.builder().value(value).build());
    }
}