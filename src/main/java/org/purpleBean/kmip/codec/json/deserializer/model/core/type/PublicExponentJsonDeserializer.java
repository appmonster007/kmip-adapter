package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.math.BigInteger;

public class PublicExponentJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<PublicExponent, BigInteger> {

    public PublicExponentJsonDeserializer() {
        super(PublicExponent.kmipTag, PublicExponent.encodingType, BigInteger.class, value -> PublicExponent.builder().value(value).build());
    }
}