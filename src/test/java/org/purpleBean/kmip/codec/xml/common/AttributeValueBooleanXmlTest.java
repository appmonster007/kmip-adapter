package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.Boolean XML Serialization Tests")
class AttributeValueBooleanXmlTest extends AbstractXmlSerializationSuite<AttributeValue.Boolean> {

    @Override
    protected Class<AttributeValue.Boolean> type() {
        return AttributeValue.Boolean.class;
    }

    @Override
    protected AttributeValue.Boolean createDefault() {
        return AttributeValue.Boolean.of(true);
    }

    @Override
    protected AttributeValue.Boolean createVariant() {
        return AttributeValue.Boolean.of(false);
    }
}
