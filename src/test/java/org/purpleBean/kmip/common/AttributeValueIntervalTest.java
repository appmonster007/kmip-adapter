package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.Interval Domain Tests")
class AttributeValueIntervalTest extends AbstractKmipDataTypeSuite<AttributeValue.Interval> {

    @Override
    protected Class<AttributeValue.Interval> type() {
        return AttributeValue.Interval.class;
    }

    @Override
    protected AttributeValue.Interval createDefault() {
        return AttributeValue.Interval.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTERVAL;
    }
}
