package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UniqueBatchItemID TTLV Serialization Tests")
class UniqueBatchItemIDTtlvTest extends AbstractTtlvSerializationTestSuite<UniqueBatchItemID> {

    @Override
    public Class<UniqueBatchItemID> type() {
        return UniqueBatchItemID.class;
    }

    @Override
    public UniqueBatchItemID createDefault() {
        return UniqueBatchItemID.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public UniqueBatchItemID createVariant() {
        return UniqueBatchItemID.of(new byte[]{0x04, 0x05, 0x06});
    }
}