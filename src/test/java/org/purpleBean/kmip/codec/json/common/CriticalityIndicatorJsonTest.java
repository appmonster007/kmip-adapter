package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CriticalityIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CriticalityIndicator JSON Serialization Tests")
class CriticalityIndicatorJsonTest extends AbstractJsonSerializationTestSuite<CriticalityIndicator> {

    @Override
    protected Class<CriticalityIndicator> type() {
        return CriticalityIndicator.class;
    }

    @Override
    protected CriticalityIndicator createDefault() {
        return CriticalityIndicator.builder().value(true).build();
    }

    @Override
    protected CriticalityIndicator createVariant() {
        return CriticalityIndicator.builder().value(false).build();
    }
}