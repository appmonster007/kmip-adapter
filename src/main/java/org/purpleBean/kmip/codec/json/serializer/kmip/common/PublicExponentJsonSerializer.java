package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentJsonSerializer extends AbstractKmipJsonSerializer<PublicExponent, BigInteger> {

    public PublicExponentJsonSerializer() {
        super(PublicExponent::getValue);
    }
}