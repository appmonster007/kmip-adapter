package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("UsageLimitsCount Domain Tests")
class UsageLimitsCountTest extends AbstractKmipDataTypeSuite<UsageLimitsCount> {

    @Override
    protected Class<UsageLimitsCount> type() {
        return UsageLimitsCount.class;
    }

    @Override
    protected UsageLimitsCount createDefault() {
        return UsageLimitsCount.builder().value(100L).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.LONG_INTEGER;
    }
}