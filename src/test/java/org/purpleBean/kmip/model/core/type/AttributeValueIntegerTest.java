package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AttributeValue.Integer Domain Tests")
class AttributeValueIntegerTest extends AbstractKmipDataTypeTestSuite<AttributeValueInteger> {

    @Override
    protected Class<AttributeValueInteger> type() {
        return AttributeValueInteger.class;
    }

    @Override
    protected AttributeValueInteger createDefault() {
        return AttributeValueInteger.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}
