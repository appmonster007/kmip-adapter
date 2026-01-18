package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerJsonDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType, BigInteger.class, value -> AttributeValueBigInteger.builder().value(value).build());
    }
}