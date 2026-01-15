package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyMaterialByteString JSON Serialization Tests")
class KeyMaterialByteStringJsonTest extends AbstractJsonSerializationTestSuite<KeyMaterialByteString> {

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