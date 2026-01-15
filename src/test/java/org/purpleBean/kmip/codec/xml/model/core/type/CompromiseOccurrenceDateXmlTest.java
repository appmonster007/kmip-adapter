package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("CompromiseOccurrenceDate XML Serialization Tests")
class CompromiseOccurrenceDateXmlTest extends AbstractXmlSerializationTestSuite<CompromiseOccurrenceDate> {

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