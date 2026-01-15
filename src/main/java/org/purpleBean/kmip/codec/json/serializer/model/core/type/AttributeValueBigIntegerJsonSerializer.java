package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerJsonSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}