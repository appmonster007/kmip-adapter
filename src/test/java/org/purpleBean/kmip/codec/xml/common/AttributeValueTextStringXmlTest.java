package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.TextString XML Serialization Tests")
class AttributeValueTextStringXmlTest extends AbstractXmlSerializationSuite<AttributeValue.TextString> {

    @Override
    protected Class<AttributeValue.TextString> type() {
        return AttributeValue.TextString.class;
    }

    @Override
    protected AttributeValue.TextString createDefault() {
        return AttributeValue.TextString.of("test");
    }

    @Override
    protected AttributeValue.TextString createVariant() {
        return AttributeValue.TextString.of("variant");
    }
}
