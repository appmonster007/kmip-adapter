package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("AttributeValue.ByteString XML Serialization Tests")
class AttributeValueByteStringXmlTest extends AbstractXmlSerializationSuite<AttributeValue.ByteString> {

    @Override
    protected Class<AttributeValue.ByteString> type() {
        return AttributeValue.ByteString.class;
    }

    @Override
    protected AttributeValue.ByteString createDefault() {
        return AttributeValue.ByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected AttributeValue.ByteString createVariant() {
        return AttributeValue.ByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}
