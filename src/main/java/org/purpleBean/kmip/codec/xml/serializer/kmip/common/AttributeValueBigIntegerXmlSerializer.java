package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerXmlSerializer extends AbstractKmipXmlSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerXmlSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}