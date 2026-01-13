package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeName XML Serialization Tests")
class AttributeNameXmlTest extends AbstractXmlSerializationTestSuite<AttributeName> {

    @Override
    protected Class<AttributeName> type() {
        return AttributeName.class;
    }

    @Override
    protected AttributeName createDefault() {
        return AttributeName.builder().value("attribute name").build();
    }

    @Override
    protected AttributeName createVariant() {
        return AttributeName.builder().value("attribute name variant").build();
    }
}
