package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("KeyMaterialByteString TTLV Serialization Tests")
class KeyMaterialByteStringTtlvTest extends AbstractTtlvSerializationSuite<KeyMaterialByteString> {

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