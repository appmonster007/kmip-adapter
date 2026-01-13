package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger Domain Tests")
class AttributeValueBigIntegerTest extends AbstractKmipDataTypeTestSuite<AttributeValueBigInteger> {

    @Override
    protected Class<AttributeValueBigInteger> type() {
        return AttributeValueBigInteger.class;
    }

    @Override
    protected AttributeValueBigInteger createDefault() {
        return AttributeValueBigInteger.of(BigInteger.valueOf(123));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
