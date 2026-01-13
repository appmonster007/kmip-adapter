package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UniqueBatchItemID;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UniqueBatchItemID TTLV Serialization Tests")
class UniqueBatchItemIDTtlvTest extends AbstractTtlvSerializationTestSuite<UniqueBatchItemID> {

    @Override
    protected Class<UniqueBatchItemID> type() {
        return UniqueBatchItemID.class;
    }

    @Override
    protected UniqueBatchItemID createDefault() {
        return UniqueBatchItemID.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected UniqueBatchItemID createVariant() {
        return UniqueBatchItemID.of(new byte[]{0x04, 0x05, 0x06});
    }
}