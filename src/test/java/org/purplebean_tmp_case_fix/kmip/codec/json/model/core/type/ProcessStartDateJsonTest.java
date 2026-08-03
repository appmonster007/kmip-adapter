package org.purplebean.kmip.codec.json.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ProcessStartDate;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProcessStartDate JSON Serialization Tests")
class ProcessStartDateJsonTest extends AbstractJsonSerializationTestSuite<ProcessStartDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<ProcessStartDate> type() {
    return ProcessStartDate.class;
  }

  @Override
  public ProcessStartDate createDefault() {
    return ProcessStartDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public ProcessStartDate createVariant() {
    return ProcessStartDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}