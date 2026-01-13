package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerTtlvSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}