package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.LongInteger TTLV Serialization Tests")
class AttributeValueLongIntegerTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.LongInteger> {

    @Override
    protected Class<AttributeValue.LongInteger> type() {
        return AttributeValue.LongInteger.class;
    }

    @Override
    protected AttributeValue.LongInteger createDefault() {
        return AttributeValue.LongInteger.of(123L);
    }

    @Override
    protected AttributeValue.LongInteger createVariant() {
        return AttributeValue.LongInteger.of(456L);
    }
}
