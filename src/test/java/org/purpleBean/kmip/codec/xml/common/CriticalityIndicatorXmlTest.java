package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CriticalityIndicator;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CriticalityIndicator XML Serialization Tests")
class CriticalityIndicatorXmlTest extends AbstractXmlSerializationTestSuite<CriticalityIndicator> {

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