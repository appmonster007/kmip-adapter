package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.TextString XML Serialization Tests")
class AttributeValueTextStringXmlTest extends AbstractXmlSerializationSuite<AttributeValueTextString> {

    @Override
    protected Class<AttributeValueTextString> type() {
        return AttributeValueTextString.class;
    }

    @Override
    protected AttributeValueTextString createDefault() {
        return AttributeValueTextString.of("test");
    }

    @Override
    protected AttributeValueTextString createVariant() {
        return AttributeValueTextString.of("variant");
    }
}
