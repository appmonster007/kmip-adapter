package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Key;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("Key TTLV Serialization Tests")
class KeyTtlvTest extends AbstractTtlvSerializationSuite<Key> {

    @Override
    protected Class<Key> type() {
        return Key.class;
    }

    @Override
    protected Key createDefault() {
        return Key.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected Key createVariant() {
        return Key.of(new byte[]{0x04, 0x05, 0x06});
    }
}