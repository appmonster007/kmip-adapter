package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueByteString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeValue.ByteString XML Serialization Tests")
class AttributeValueByteStringXmlTest extends AbstractXmlSerializationTestSuite<AttributeValueByteString> {

    @Override
    protected Class<AttributeValueByteString> type() {
        return AttributeValueByteString.class;
    }

    @Override
    protected AttributeValueByteString createDefault() {
        return AttributeValueByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected AttributeValueByteString createVariant() {
        return AttributeValueByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}
