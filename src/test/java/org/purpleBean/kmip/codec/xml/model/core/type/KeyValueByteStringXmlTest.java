package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyValueByteString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValue.ByteString XML Serialization Tests")
class KeyValueByteStringXmlTest extends AbstractXmlSerializationTestSuite<KeyValueByteString> {

    @Override
    public Class<KeyValueByteString> type() {
        return KeyValueByteString.class;
    }

    @Override
    public KeyValueByteString createDefault() {
        return KeyValueByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public KeyValueByteString createVariant() {
        return KeyValueByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}