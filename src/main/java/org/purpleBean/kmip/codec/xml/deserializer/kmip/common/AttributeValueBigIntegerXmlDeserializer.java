package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerXmlDeserializer extends AbstractKmipXmlDeserializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerXmlDeserializer() {
        super(AttributeValueBigInteger.kmipTag, AttributeValueBigInteger.encodingType, BigInteger.class, value -> AttributeValueBigInteger.builder().value(value).build());
    }
}