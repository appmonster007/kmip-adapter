package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeValue.Integer XML Serialization Tests")
class AttributeValueIntegerXmlTest extends AbstractXmlSerializationTestSuite<AttributeValueInteger> {

    @Override
    protected Class<AttributeValueInteger> type() {
        return AttributeValueInteger.class;
    }

    @Override
    protected AttributeValueInteger createDefault() {
        return AttributeValueInteger.of(123);
    }

    @Override
    protected AttributeValueInteger createVariant() {
        return AttributeValueInteger.of(456);
    }
}
