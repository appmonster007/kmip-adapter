package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.Integer XML Serialization Tests")
class AttributeValueIntegerXmlTest extends AbstractXmlSerializationSuite<AttributeValue.Integer> {

    @Override
    protected Class<AttributeValue.Integer> type() {
        return AttributeValue.Integer.class;
    }

    @Override
    protected AttributeValue.Integer createDefault() {
        return AttributeValue.Integer.of(123);
    }

    @Override
    protected AttributeValue.Integer createVariant() {
        return AttributeValue.Integer.of(456);
    }
}
