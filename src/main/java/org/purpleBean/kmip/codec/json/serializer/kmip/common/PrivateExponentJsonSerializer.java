package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentJsonSerializer extends AbstractKmipJsonSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentJsonSerializer() {
        super(PrivateExponent::getValue);
    }
}