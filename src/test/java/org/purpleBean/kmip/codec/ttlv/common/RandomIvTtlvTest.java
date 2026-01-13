package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.RandomIv;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RandomIv TTLV Serialization Tests")
class RandomIvTtlvTest extends AbstractTtlvSerializationTestSuite<RandomIv> {

    @Override
    protected Class<RandomIv> type() {
        return RandomIv.class;
    }

    @Override
    protected RandomIv createDefault() {
        return RandomIv.of(true);
    }

    @Override
    protected RandomIv createVariant() {
        return RandomIv.of(false);
    }
}