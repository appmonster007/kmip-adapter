package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueInterval;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.Interval JSON Serialization Tests")
class AttributeValueIntervalJsonTest extends AbstractJsonSerializationSuite<AttributeValueInterval> {

    @Override
    protected Class<AttributeValueInterval> type() {
        return AttributeValueInterval.class;
    }

    @Override
    protected AttributeValueInterval createDefault() {
        return AttributeValueInterval.of(123);
    }

    @Override
    protected AttributeValueInterval createVariant() {
        return AttributeValueInterval.of(456);
    }
}
