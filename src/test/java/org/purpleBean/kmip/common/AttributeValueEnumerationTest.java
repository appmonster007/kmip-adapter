package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.Enumeration Domain Tests")
class AttributeValueEnumerationTest extends AbstractKmipDataTypeSuite<AttributeValueEnumeration> {

    @Override
    protected Class<AttributeValueEnumeration> type() {
        return AttributeValueEnumeration.class;
    }

    @Override
    protected AttributeValueEnumeration createDefault() {
        return AttributeValueEnumeration.of(123);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }
}
