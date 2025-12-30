package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.Interval JSON Serialization Tests")
class AttributeValueIntervalJsonTest extends AbstractJsonSerializationSuite<AttributeValue.Interval> {

    @Override
    protected Class<AttributeValue.Interval> type() {
        return AttributeValue.Interval.class;
    }

    @Override
    protected AttributeValue.Interval createDefault() {
        return AttributeValue.Interval.of(123);
    }

    @Override
    protected AttributeValue.Interval createVariant() {
        return AttributeValue.Interval.of(456);
    }
}
