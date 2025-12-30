package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.ByteString Domain Tests")
class AttributeValueByteStringTest extends AbstractKmipDataTypeSuite<AttributeValue.ByteString> {

    @Override
    protected Class<AttributeValue.ByteString> type() {
        return AttributeValue.ByteString.class;
    }

    @Override
    protected AttributeValue.ByteString createDefault() {
        return AttributeValue.ByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}
