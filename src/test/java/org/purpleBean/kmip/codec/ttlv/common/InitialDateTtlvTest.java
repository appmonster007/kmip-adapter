package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InitialDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("InitialDate TTLV Serialization Tests")
class InitialDateTtlvTest extends AbstractTtlvSerializationSuite<InitialDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<InitialDate> type() {
        return InitialDate.class;
    }

    @Override
    protected InitialDate createDefault() {
        return InitialDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected InitialDate createVariant() {
        return InitialDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}