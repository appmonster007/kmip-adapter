package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyMaterial;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyMaterial.ByteString XML Serialization Tests")
class KeyMaterialByteStringXmlTest extends AbstractXmlSerializationSuite<KeyMaterial.ByteString> {

    @Override
    protected Class<KeyMaterial.ByteString> type() {
        return KeyMaterial.ByteString.class;
    }

    @Override
    protected KeyMaterial.ByteString createDefault() {
        return KeyMaterial.ByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected KeyMaterial.ByteString createVariant() {
        return KeyMaterial.ByteString.of(new byte[]{0x04, 0x05, 0x06});
    }
}