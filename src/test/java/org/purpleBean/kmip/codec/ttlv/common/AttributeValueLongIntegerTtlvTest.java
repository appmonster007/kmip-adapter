package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueLongInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.LongInteger TTLV Serialization Tests")
class AttributeValueLongIntegerTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueLongInteger> {

    @Override
    protected Class<AttributeValueLongInteger> type() {
        return AttributeValueLongInteger.class;
    }

    @Override
    protected AttributeValueLongInteger createDefault() {
        return AttributeValueLongInteger.of(123L);
    }

    @Override
    protected AttributeValueLongInteger createVariant() {
        return AttributeValueLongInteger.of(456L);
    }
}
