package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("KeyMaterial.ByteString Domain Tests")
class KeyMaterialByteStringTest extends AbstractKmipDataTypeSuite<KeyMaterial.ByteString> {

    @Override
    protected Class<KeyMaterial.ByteString> type() {
        return KeyMaterial.ByteString.class;
    }

    @Override
    protected KeyMaterial.ByteString createDefault() {
        return KeyMaterial.ByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}