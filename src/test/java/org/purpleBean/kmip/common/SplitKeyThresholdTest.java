package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SplitKeyThreshold Domain Tests")
class SplitKeyThresholdTest extends AbstractKmipDataTypeTestSuite<SplitKeyThreshold> {

    @Override
    protected Class<SplitKeyThreshold> type() {
        return SplitKeyThreshold.class;
    }

    @Override
    protected SplitKeyThreshold createDefault() {
        return SplitKeyThreshold.builder().value(2).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}