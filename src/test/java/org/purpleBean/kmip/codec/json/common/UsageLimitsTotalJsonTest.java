package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("UsageLimitsTotal JSON Serialization Tests")
class UsageLimitsTotalJsonTest extends AbstractJsonSerializationSuite<UsageLimitsTotal> {

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