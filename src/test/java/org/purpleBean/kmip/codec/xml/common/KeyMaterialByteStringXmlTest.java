package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyMaterialByteString XML Serialization Tests")
class KeyMaterialByteStringXmlTest extends AbstractXmlSerializationSuite<KeyMaterialByteString> {

    @Override
    protected Class<KeyMaterialByteString> type() {
        return KeyMaterialByteString.class;
    }

    @Override
    protected KeyMaterialByteString createDefault() {
        return KeyMaterialByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected KeyMaterialByteString createVariant() {
        return KeyMaterialByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}