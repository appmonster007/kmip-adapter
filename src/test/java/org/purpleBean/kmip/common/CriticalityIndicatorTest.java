package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("CriticalityIndicator Domain Tests")
class CriticalityIndicatorTest extends AbstractKmipDataTypeSuite<CriticalityIndicator> {

    @Override
    protected Class<CriticalityIndicator> type() {
        return CriticalityIndicator.class;
    }

    @Override
    protected CriticalityIndicator createDefault() {
        return CriticalityIndicator.builder().value(true).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}