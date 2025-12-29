package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SplitKeyThreshold;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("SplitKeyThreshold JSON Serialization Tests")
class SplitKeyThresholdJsonTest extends AbstractJsonSerializationSuite<SplitKeyThreshold> {

    @Override
    protected Class<SplitKeyThreshold> type() {
        return SplitKeyThreshold.class;
    }

    @Override
    protected SplitKeyThreshold createDefault() {
        return SplitKeyThreshold.builder().value(2).build();
    }

    @Override
    protected SplitKeyThreshold createVariant() {
        return SplitKeyThreshold.builder().value(3).build();
    }
}