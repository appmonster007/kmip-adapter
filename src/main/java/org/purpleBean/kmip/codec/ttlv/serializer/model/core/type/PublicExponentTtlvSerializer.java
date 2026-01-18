package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.math.BigInteger;

public class PublicExponentTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<PublicExponent, BigInteger> {

    public PublicExponentTtlvSerializer() {
        super(PublicExponent::getValue);
    }
}