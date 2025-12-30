package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.math.BigInteger;

@DisplayName("AttributeValue.BigInteger Domain Tests")
class AttributeValueBigIntegerTest extends AbstractKmipDataTypeSuite<AttributeValue.BigInteger> {

    @Override
    protected Class<AttributeValue.BigInteger> type() {
        return AttributeValue.BigInteger.class;
    }

    @Override
    protected AttributeValue.BigInteger createDefault() {
        return AttributeValue.BigInteger.of(BigInteger.valueOf(123));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BIG_INTEGER;
    }
}
