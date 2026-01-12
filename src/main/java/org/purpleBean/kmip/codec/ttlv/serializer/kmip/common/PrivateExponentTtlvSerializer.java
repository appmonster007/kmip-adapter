package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentTtlvSerializer extends AbstractKmipTtlvSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentTtlvSerializer() {
        super(PrivateExponent::getValue);
    }
}