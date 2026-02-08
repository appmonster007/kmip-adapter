package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CompromiseOccurrenceDate TTLV Serialization Tests")
class CompromiseOccurrenceDateTtlvTest extends AbstractTtlvSerializationTestSuite<CompromiseOccurrenceDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    public Class<CompromiseOccurrenceDate> type() {
        return CompromiseOccurrenceDate.class;
    }

    @Override
    public CompromiseOccurrenceDate createDefault() {
        return CompromiseOccurrenceDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    public CompromiseOccurrenceDate createVariant() {
        return CompromiseOccurrenceDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}