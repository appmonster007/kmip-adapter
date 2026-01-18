package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentTtlvSerializer() {
        super(PrivateExponent::getValue);
    }
}