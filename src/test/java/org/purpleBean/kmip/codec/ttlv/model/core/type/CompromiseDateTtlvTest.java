package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CompromiseDate;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CompromiseDate TTLV Serialization Tests")
class CompromiseDateTtlvTest extends AbstractTtlvSerializationTestSuite<CompromiseDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<CompromiseDate> type() {
    return CompromiseDate.class;
  }

  @Override
  public CompromiseDate createDefault() {
    return CompromiseDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public CompromiseDate createVariant() {
    return CompromiseDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}