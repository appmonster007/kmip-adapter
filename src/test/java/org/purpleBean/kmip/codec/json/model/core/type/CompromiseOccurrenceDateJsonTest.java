package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CompromiseOccurrenceDate JSON Serialization Tests")
class CompromiseOccurrenceDateJsonTest extends AbstractJsonSerializationTestSuite<CompromiseOccurrenceDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<CompromiseOccurrenceDate> type() {
        return CompromiseOccurrenceDate.class;
    }

    @Override
    protected CompromiseOccurrenceDate createDefault() {
        return CompromiseOccurrenceDate.builder()
                .value(FIXED_TIME)
                .build();
    }

    @Override
    protected CompromiseOccurrenceDate createVariant() {
        return CompromiseOccurrenceDate.builder()
                .value(FIXED_TIME.plusDays(1))
                .build();
    }
}