package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueLongInteger;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeValue.LongInteger JSON Serialization Tests")
class AttributeValueLongIntegerJsonTest extends AbstractJsonSerializationTestSuite<AttributeValueLongInteger> {

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
