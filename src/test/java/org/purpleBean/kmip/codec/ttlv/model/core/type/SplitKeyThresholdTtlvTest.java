package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SplitKeyThreshold TTLV Serialization Tests")
class SplitKeyThresholdTtlvTest extends AbstractTtlvSerializationTestSuite<SplitKeyThreshold> {

    @Override
    public Class<SplitKeyThreshold> type() {
        return SplitKeyThreshold.class;
    }

    @Override
    public SplitKeyThreshold createDefault() {
        return SplitKeyThreshold.builder().value(2).build();
    }

    @Override
    public SplitKeyThreshold createVariant() {
        return SplitKeyThreshold.builder().value(3).build();
    }
}