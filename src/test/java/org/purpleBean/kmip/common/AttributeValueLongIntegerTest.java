package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AttributeValue.LongInteger Domain Tests")
class AttributeValueLongIntegerTest extends AbstractKmipDataTypeTestSuite<AttributeValueLongInteger> {

    @Override
    protected Class<AttributeValueLongInteger> type() {
        return AttributeValueLongInteger.class;
    }

    @Override
    protected AttributeValueLongInteger createDefault() {
        return AttributeValueLongInteger.of(123L);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.LONG_INTEGER;
    }
}
