package org.purplebean.kmip.codec.json.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CompromiseOccurrenceDate JSON Serialization Tests")
class CompromiseOccurrenceDateJsonTest
    extends AbstractJsonSerializationTestSuite<CompromiseOccurrenceDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<CompromiseOccurrenceDate> type() {
    return CompromiseOccurrenceDate.class;
  }

  @Override
  public CompromiseOccurrenceDate createDefault() {
    return CompromiseOccurrenceDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public CompromiseOccurrenceDate createVariant() {
    return CompromiseOccurrenceDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}