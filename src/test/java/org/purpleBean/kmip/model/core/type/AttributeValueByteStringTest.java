package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AttributeValue.ByteString Domain Tests")
class AttributeValueByteStringTest extends AbstractKmipDataTypeTestSuite<AttributeValueByteString> {

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
