package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IVCounterNonce;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("IVCounterNonce TTLV Serialization Tests")
class IVCounterNonceTtlvTest extends AbstractTtlvSerializationTestSuite<IVCounterNonce> {

    @Override
    protected Class<IVCounterNonce> type() {
        return IVCounterNonce.class;
    }

    @Override
    protected IVCounterNonce createDefault() {
        return IVCounterNonce.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected IVCounterNonce createVariant() {
        return IVCounterNonce.of(new byte[]{0x04, 0x05, 0x06});
    }
}