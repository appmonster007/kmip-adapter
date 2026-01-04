package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.TimeStamp;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TimeStamp TTLV Serialization Tests")
class TimeStampTtlvTest extends AbstractTtlvSerializationSuite<TimeStamp> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<TimeStamp> type() {
        return TimeStamp.class;
    }

    @Override
    protected TimeStamp createDefault() {
        // TODO: Update with actual default values for your dataType
        return TimeStamp.builder().value(FIXED_TIME).build();
    }

    @Override
    protected TimeStamp createVariant() {
        // TODO: Update with different values to test variations
        return TimeStamp.builder().value(FIXED_TIME.plusDays(1)).build();
    }
}