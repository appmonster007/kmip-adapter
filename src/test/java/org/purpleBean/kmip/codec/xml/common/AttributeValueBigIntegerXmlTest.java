package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger XML Serialization Tests")
class AttributeValueBigIntegerXmlTest extends AbstractXmlSerializationSuite<AttributeValue.BigInteger> {

    @Override
    protected Class<AttributeValue.BigInteger> type() {
        return AttributeValue.BigInteger.class;
    }

    @Override
    protected AttributeValue.BigInteger createDefault() {
        return AttributeValue.BigInteger.of(BigInteger.valueOf(123));
    }

    @Override
    protected AttributeValue.BigInteger createVariant() {
        return AttributeValue.BigInteger.of(BigInteger.valueOf(456));
    }
}
