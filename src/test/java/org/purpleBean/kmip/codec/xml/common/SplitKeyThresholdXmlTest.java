package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SplitKeyThreshold;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SplitKeyThreshold XML Serialization Tests")
class SplitKeyThresholdXmlTest extends AbstractXmlSerializationTestSuite<SplitKeyThreshold> {

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