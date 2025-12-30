package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.LongInteger Domain Tests")
class AttributeValueLongIntegerTest extends AbstractKmipDataTypeSuite<AttributeValue.LongInteger> {

    @Override
    protected Class<AttributeValue.LongInteger> type() {
        return AttributeValue.LongInteger.class;
    }

    @Override
    protected AttributeValue.LongInteger createDefault() {
        return AttributeValue.LongInteger.of(123L);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.LONG_INTEGER;
    }
}
