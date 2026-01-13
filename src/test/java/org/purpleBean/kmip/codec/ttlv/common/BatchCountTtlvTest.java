package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.BatchCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BatchCount TTLV Serialization Tests")
class BatchCountTtlvTest extends AbstractTtlvSerializationTestSuite<BatchCount> {

    @Override
    protected Class<BatchCount> type() {
        return BatchCount.class;
    }

    @Override
    protected BatchCount createDefault() {
        return BatchCount.builder().value(5).build();
    }

    @Override
    protected BatchCount createVariant() {
        return BatchCount.builder().value(10).build();
    }
}