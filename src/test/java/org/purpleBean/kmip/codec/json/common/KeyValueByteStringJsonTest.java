package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValueByteString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("KeyValue.ByteString JSON Serialization Tests")
class KeyValueByteStringJsonTest extends AbstractJsonSerializationSuite<KeyValueByteString> {

    @Override
    protected Class<KeyValueByteString> type() {
        return KeyValueByteString.class;
    }

    @Override
    protected KeyValueByteString createDefault() {
        return KeyValueByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected KeyValueByteString createVariant() {
        return KeyValueByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}