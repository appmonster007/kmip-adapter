package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.Enumeration Domain Tests")
class AttributeValueEnumerationTest extends AbstractKmipDataTypeSuite<AttributeValue.Enumeration> {

    @Override
    protected Class<AttributeValue.Enumeration> type() {
        return AttributeValue.Enumeration.class;
    }

    @Override
    protected AttributeValue.Enumeration createDefault() {
        return AttributeValue.Enumeration.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }
}
