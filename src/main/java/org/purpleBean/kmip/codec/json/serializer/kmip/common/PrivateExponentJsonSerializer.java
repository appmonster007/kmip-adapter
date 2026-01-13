package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PrivateExponent;

import java.math.BigInteger;

public class PrivateExponentJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PrivateExponent, BigInteger> {

    public PrivateExponentJsonSerializer() {
        super(PrivateExponent::getValue);
    }
}