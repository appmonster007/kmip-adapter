package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyMaterial;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("KeyMaterial.ByteString TTLV Serialization Tests")
class KeyMaterialByteStringTtlvTest extends AbstractTtlvSerializationSuite<KeyMaterial.ByteString> {

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