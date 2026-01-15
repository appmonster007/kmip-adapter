package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UsageLimitsCount JSON Serialization Tests")
class UsageLimitsCountJsonTest extends AbstractJsonSerializationTestSuite<UsageLimitsCount> {

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