package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeValue Xml Serialization Tests")
class AttributeValueXmlTest extends AbstractXmlSerializationTestSuite<AttributeValue> {

    @Override
    protected Class<AttributeValue> type() {
        return AttributeValue.class;
    }

    @Override
    protected AttributeValue createDefault() {
        return AttributeValue.ofTextString("default-string");
    }

    @Override
    protected AttributeValue createVariant() {
        return AttributeValue.ofInteger(123);
    }
}