package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValue.ByteString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("KeyValue.ByteString TTLV Serialization Tests")
class KeyValueByteStringTtlvTest extends AbstractTtlvSerializationSuite<KeyValue.ByteString> {

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