package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.Interval XML Serialization Tests")
class AttributeValueIntervalXmlTest extends AbstractXmlSerializationSuite<AttributeValue.Interval> {

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
