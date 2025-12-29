package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("KeyValue.ByteString JSON Serialization Tests")
class KeyValueByteStringJsonTest extends AbstractJsonSerializationSuite<KeyValue.ByteString> {

    @Override
    protected Class<KeyValue.ByteString> type() {
        return KeyValue.ByteString.class;
    }

    @Override
    protected KeyValue.ByteString createDefault() {
        return KeyValue.ByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected KeyValue.ByteString createVariant() {
        return KeyValue.ByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}