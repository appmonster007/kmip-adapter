package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueInterval;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Interval TTLV Serialization Tests")
class AttributeValueIntervalTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueInterval> {

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
