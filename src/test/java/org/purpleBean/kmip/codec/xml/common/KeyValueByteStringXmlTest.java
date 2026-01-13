package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValueByteString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValue.ByteString XML Serialization Tests")
class KeyValueByteStringXmlTest extends AbstractXmlSerializationTestSuite<KeyValueByteString> {

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