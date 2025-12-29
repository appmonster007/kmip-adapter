package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("UsageLimitsCount XML Serialization Tests")
class UsageLimitsCountXmlTest extends AbstractXmlSerializationSuite<UsageLimitsCount> {

    @Override
    protected Class<UsageLimitsCount> type() {
        return UsageLimitsCount.class;
    }

    @Override
    protected UsageLimitsCount createDefault() {
        return UsageLimitsCount.builder().value(100L).build();
    }

    @Override
    protected UsageLimitsCount createVariant() {
        return UsageLimitsCount.builder().value(200L).build();
    }
}