package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.TextString Domain Tests")
class AttributeValueTextStringTest extends AbstractKmipDataTypeSuite<AttributeValue.TextString> {

    @Override
    protected Class<AttributeValue.TextString> type() {
        return AttributeValue.TextString.class;
    }

    @Override
    protected AttributeValue.TextString createDefault() {
        return AttributeValue.TextString.of("test");
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}
