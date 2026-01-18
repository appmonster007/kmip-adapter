package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerTtlvSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}