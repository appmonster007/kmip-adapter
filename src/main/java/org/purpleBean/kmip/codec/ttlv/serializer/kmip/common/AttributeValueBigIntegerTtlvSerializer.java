package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerTtlvSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}