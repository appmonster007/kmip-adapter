package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<PrivateExponent, BigInteger> {

    public PrivateExponentTtlvDeserializer() {
        super(PrivateExponent.kmipTag, PrivateExponent.encodingType, BigInteger.class, value -> PrivateExponent.builder().value(value).build());
    }
}