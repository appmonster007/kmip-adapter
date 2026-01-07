package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyMaterial;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("KeyMaterialByteString JSON Serialization Tests")
class KeyMaterialByteStringJsonTest extends AbstractJsonSerializationSuite<KeyMaterial.ByteString> {

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