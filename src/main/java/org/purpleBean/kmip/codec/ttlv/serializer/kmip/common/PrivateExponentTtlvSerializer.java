package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentTtlvSerializer() {
        super(PrivateExponent::getValue);
    }
}