package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.ByteString JSON Serialization Tests")
class AttributeValueByteStringJsonTest extends AbstractJsonSerializationSuite<AttributeValue.ByteString> {

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
