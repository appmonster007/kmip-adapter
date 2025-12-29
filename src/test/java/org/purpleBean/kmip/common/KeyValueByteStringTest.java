package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.nio.ByteBuffer;
import java.util.List;

@DisplayName("KeyValue.ByteString Domain Tests")
class KeyValueByteStringTest extends AbstractKmipDataTypeSuite<KeyValue.ByteString> {

    @Override
    protected Class<KeyValue.ByteString> type() {
        return KeyValue.ByteString.class;
    }

    @Override
    protected KeyValue.ByteString createDefault() {
        return KeyValue.ByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}