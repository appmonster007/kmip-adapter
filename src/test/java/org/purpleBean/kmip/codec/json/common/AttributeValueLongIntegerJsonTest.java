package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.LongInteger JSON Serialization Tests")
class AttributeValueLongIntegerJsonTest extends AbstractJsonSerializationSuite<AttributeValue.LongInteger> {

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
