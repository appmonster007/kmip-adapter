package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueInterval;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.Interval XML Serialization Tests")
class AttributeValueIntervalXmlTest extends AbstractXmlSerializationSuite<AttributeValueInterval> {

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
