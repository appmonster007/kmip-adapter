package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ProcessStartDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ProcessStartDate JSON Serialization Tests")
class ProcessStartDateJsonTest extends AbstractJsonSerializationTestSuite<ProcessStartDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<ProcessStartDate> type() {
        return ProcessStartDate.class;
    }

    @Override
    protected ProcessStartDate createDefault() {
        return ProcessStartDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected ProcessStartDate createVariant() {
        return ProcessStartDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}