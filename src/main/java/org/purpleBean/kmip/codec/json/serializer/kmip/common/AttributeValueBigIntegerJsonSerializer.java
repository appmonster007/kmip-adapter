package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerJsonSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}