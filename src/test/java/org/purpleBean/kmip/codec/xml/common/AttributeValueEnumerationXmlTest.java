package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueEnumeration;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeValue.Enumeration XML Serialization Tests")
class AttributeValueEnumerationXmlTest extends AbstractXmlSerializationTestSuite<AttributeValueEnumeration> {

    @Override
    protected Class<AttributeValueEnumeration> type() {
        return AttributeValueEnumeration.class;
    }

    @Override
    protected AttributeValueEnumeration createDefault() {
        return AttributeValueEnumeration.of(123);
    }

    @Override
    protected AttributeValueEnumeration createVariant() {
        return AttributeValueEnumeration.of(456);
    }
}
