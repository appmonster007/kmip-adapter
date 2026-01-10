package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueBigInteger;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger XML Serialization Tests")
class AttributeValueBigIntegerXmlTest extends AbstractXmlSerializationSuite<AttributeValueBigInteger> {

    @Override
    protected Class<AttributeValueBigInteger> type() {
        return AttributeValueBigInteger.class;
    }

    @Override
    protected AttributeValueBigInteger createDefault() {
        return AttributeValueBigInteger.of(BigInteger.valueOf(123));
    }

    @Override
    protected AttributeValueBigInteger createVariant() {
        return AttributeValueBigInteger.of(BigInteger.valueOf(456));
    }
}
