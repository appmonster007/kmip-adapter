package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CompromiseDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CompromiseDate TTLV Serialization Tests")
class CompromiseDateTtlvTest extends AbstractTtlvSerializationTestSuite<CompromiseDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<CompromiseDate> type() {
        return CompromiseDate.class;
    }

    @Override
    protected CompromiseDate createDefault() {
        return CompromiseDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected CompromiseDate createVariant() {
        return CompromiseDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}