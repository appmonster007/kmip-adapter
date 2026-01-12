package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentTtlvSerializer extends AbstractKmipTtlvSerializer<PublicExponent, BigInteger> {

    public PublicExponentTtlvSerializer() {
        super(PublicExponent::getValue);
    }
}