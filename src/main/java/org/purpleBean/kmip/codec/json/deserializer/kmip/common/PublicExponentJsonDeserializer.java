package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PublicExponent, BigInteger> {

    public PublicExponentJsonDeserializer() {
        super(PublicExponent.kmipTag, PublicExponent.encodingType, BigInteger.class, value -> PublicExponent.builder().value(value).build());
    }
}