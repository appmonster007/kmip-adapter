package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueBoolean;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeValue.Boolean XML Serialization Tests")
class AttributeValueBooleanXmlTest extends AbstractXmlSerializationTestSuite<AttributeValueBoolean> {

    @Override
    protected Class<AttributeValueBoolean> type() {
        return AttributeValueBoolean.class;
    }

    @Override
    protected AttributeValueBoolean createDefault() {
        return AttributeValueBoolean.of(true);
    }

    @Override
    protected AttributeValueBoolean createVariant() {
        return AttributeValueBoolean.of(false);
    }
}
