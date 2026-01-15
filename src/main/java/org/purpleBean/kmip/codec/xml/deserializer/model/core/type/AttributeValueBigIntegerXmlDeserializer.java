package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerXmlDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType, BigInteger.class, value -> AttributeValueBigInteger.builder().value(value).build());
    }
}