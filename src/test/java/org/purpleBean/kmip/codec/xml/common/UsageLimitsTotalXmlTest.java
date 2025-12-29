package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("UsageLimitsTotal XML Serialization Tests")
class UsageLimitsTotalXmlTest extends AbstractXmlSerializationSuite<UsageLimitsTotal> {

    @Override
    protected Class<UsageLimitsTotal> type() {
        return UsageLimitsTotal.class;
    }

    @Override
    protected UsageLimitsTotal createDefault() {
        return UsageLimitsTotal.builder().value(1000L).build();
    }

    @Override
    protected UsageLimitsTotal createVariant() {
        return UsageLimitsTotal.builder().value(2000L).build();
    }
}