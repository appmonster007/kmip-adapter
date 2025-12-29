package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("DigestValue TTLV Serialization Tests")
class DigestValueTtlvTest extends AbstractTtlvSerializationSuite<DigestValue> {

    @Override
    protected Class<DigestValue> type() {
        return DigestValue.class;
    }

    @Override
    protected DigestValue createDefault() {
        return DigestValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected DigestValue createVariant() {
        return DigestValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}