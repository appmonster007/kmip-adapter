package org.purplebean.kmip.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("TimeStamp Domain Tests")
class TimeStampTest extends AbstractKmipDataTypeTestSuite<TimeStamp> {

  @Override
  protected Class<TimeStamp> type() {
    return TimeStamp.class;
  }

  @Override
  protected TimeStamp createDefault() {
    OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    return TimeStamp
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.DATE_TIME;
  }
}