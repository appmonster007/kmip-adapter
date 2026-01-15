package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValueLongInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeValue.LongInteger TTLV Serialization Tests")
class AttributeValueLongIntegerTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValueLongInteger> {

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
