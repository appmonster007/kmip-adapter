package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueByteString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.ByteString TTLV Serialization Tests")
class AttributeValueByteStringTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueByteString> {

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
