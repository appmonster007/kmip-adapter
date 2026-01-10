package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.Interval Domain Tests")
class AttributeValueIntervalTest extends AbstractKmipDataTypeSuite<AttributeValueInterval> {

    @Override
    protected Class<AttributeValueInterval> type() {
        return AttributeValueInterval.class;
    }

    @Override
    protected AttributeValueInterval createDefault() {
        return AttributeValueInterval.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTERVAL;
    }
}
