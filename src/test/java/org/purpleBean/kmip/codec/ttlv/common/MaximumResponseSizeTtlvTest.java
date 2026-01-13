package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MaximumResponseSize;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MaximumResponseSize TTLV Serialization Tests")
class MaximumResponseSizeTtlvTest extends AbstractTtlvSerializationTestSuite<MaximumResponseSize> {

    @Override
    protected Class<MaximumResponseSize> type() {
        return MaximumResponseSize.class;
    }

    @Override
    protected MaximumResponseSize createDefault() {
        return MaximumResponseSize.builder().value(1024).build();
    }

    @Override
    protected MaximumResponseSize createVariant() {
        return MaximumResponseSize.builder().value(2048).build();
    }
}