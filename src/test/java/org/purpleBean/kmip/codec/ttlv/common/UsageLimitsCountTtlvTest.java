package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("UsageLimitsCount TTLV Serialization Tests")
class UsageLimitsCountTtlvTest extends AbstractTtlvSerializationSuite<UsageLimitsCount> {

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