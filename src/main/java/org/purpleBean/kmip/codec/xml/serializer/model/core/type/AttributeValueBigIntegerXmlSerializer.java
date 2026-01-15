package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBigInteger;

import java.math.BigInteger;

public class AttributeValueBigIntegerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttributeValueBigInteger, BigInteger> {

    public AttributeValueBigIntegerXmlSerializer() {
        super(AttributeValueBigInteger::getValue);
    }
}