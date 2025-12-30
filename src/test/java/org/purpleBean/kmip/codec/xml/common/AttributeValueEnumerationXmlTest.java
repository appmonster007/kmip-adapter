package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.Enumeration XML Serialization Tests")
class AttributeValueEnumerationXmlTest extends AbstractXmlSerializationSuite<AttributeValue.Enumeration> {

    @Override
    protected Class<AttributeValue.Enumeration> type() {
        return AttributeValue.Enumeration.class;
    }

    @Override
    protected AttributeValue.Enumeration createDefault() {
        return AttributeValue.Enumeration.of(123);
    }

    @Override
    protected AttributeValue.Enumeration createVariant() {
        return AttributeValue.Enumeration.of(456);
    }
}
