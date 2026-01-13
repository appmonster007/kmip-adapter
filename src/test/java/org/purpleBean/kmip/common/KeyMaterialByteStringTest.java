package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("KeyMaterial.ByteString Domain Tests")
class KeyMaterialByteStringTest extends AbstractKmipDataTypeTestSuite<KeyMaterialByteString> {

    @Override
    protected Class<KeyMaterialByteString> type() {
        return KeyMaterialByteString.class;
    }

    @Override
    protected KeyMaterialByteString createDefault() {
        return KeyMaterialByteString.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}