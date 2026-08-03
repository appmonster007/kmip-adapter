package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.InitialDate;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InitialDate TTLV Serialization Tests")
class InitialDateTtlvTest extends AbstractTtlvSerializationTestSuite<InitialDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<InitialDate> type() {
    return InitialDate.class;
  }

  @Override
  public InitialDate createDefault() {
    return InitialDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public InitialDate createVariant() {
    return InitialDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}