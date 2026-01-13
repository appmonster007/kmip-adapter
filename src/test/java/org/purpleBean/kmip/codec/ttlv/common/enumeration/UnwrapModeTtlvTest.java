package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UnwrapMode;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UnwrapMode TTLV Serialization")
class UnwrapModeTtlvTest extends AbstractTtlvSerializationTestSuite<UnwrapMode> {
    @Override
    protected Class<UnwrapMode> type() {
        return UnwrapMode.class;
    }

    @Override
    protected UnwrapMode createDefault() {
        return new UnwrapMode(UnwrapMode.Standard.UNSPECIFIED);
    }

    @Override
    protected UnwrapMode createVariant() {
        return new UnwrapMode(UnwrapMode.Standard.PROCESSED);
    }
}
