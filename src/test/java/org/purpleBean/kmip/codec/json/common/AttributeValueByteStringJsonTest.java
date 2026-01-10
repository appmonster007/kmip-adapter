package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueByteString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.ByteString JSON Serialization Tests")
class AttributeValueByteStringJsonTest extends AbstractJsonSerializationSuite<AttributeValueByteString> {

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
