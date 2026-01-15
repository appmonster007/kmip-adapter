package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("KeyValue.ByteString Domain Tests")
class KeyValueByteStringTest extends AbstractKmipDataTypeTestSuite<KeyValueByteString> {

    @Override
    protected Class<KeyValueByteString> type() {
        return KeyValueByteString.class;
    }

    @Override
    protected KeyValueByteString createDefault() {
        return KeyValueByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}