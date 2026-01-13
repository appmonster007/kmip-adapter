package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerXmlSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}