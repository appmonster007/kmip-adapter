package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.ByteString Domain Tests")
class AttributeValueByteStringTest extends AbstractKmipDataTypeSuite<AttributeValueByteString> {

    @Override
    protected Class<AttributeValueByteString> type() {
        return AttributeValueByteString.class;
    }

    @Override
    protected AttributeValueByteString createDefault() {
        return AttributeValueByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}
