package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("TimeStamp Domain Tests")
class TimeStampTest extends AbstractKmipDataTypeTestSuite<TimeStamp> {

    @Override
    protected Class<TimeStamp> type() {
        return TimeStamp.class;
    }

    @Override
    protected TimeStamp createDefault() {
        // TODO: Update with actual default values for your dataType
        OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        return TimeStamp.builder().value(FIXED_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        // TODO: Update with actual encoding type for your dataType
        return EncodingType.DATE_TIME;
    }
}