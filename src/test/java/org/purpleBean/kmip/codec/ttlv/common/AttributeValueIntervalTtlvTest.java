package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Interval TTLV Serialization Tests")
class AttributeValueIntervalTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.Interval> {

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
