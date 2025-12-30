package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.Integer Domain Tests")
class AttributeValueIntegerTest extends AbstractKmipDataTypeSuite<AttributeValue.Integer> {

    @Override
    protected Class<AttributeValue.Integer> type() {
        return AttributeValue.Integer.class;
    }

    @Override
    protected AttributeValue.Integer createDefault() {
        return AttributeValue.Integer.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}
