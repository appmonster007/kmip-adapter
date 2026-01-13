package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.PublicExponent;

import java.math.BigInteger;

public class PublicExponentJsonSerializer extends AbstractKmipDataTypeJsonSerializer<PublicExponent, BigInteger> {

    public PublicExponentJsonSerializer() {
        super(PublicExponent::getValue);
    }
}