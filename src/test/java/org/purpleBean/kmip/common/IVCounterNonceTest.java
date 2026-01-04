package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("IVCounterNonce Domain Tests")
class IVCounterNonceTest extends AbstractKmipDataTypeSuite<IVCounterNonce> {

    @Override
    protected Class<IVCounterNonce> type() {
        return IVCounterNonce.class;
    }

    @Override
    protected IVCounterNonce createDefault() {
        return IVCounterNonce.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}