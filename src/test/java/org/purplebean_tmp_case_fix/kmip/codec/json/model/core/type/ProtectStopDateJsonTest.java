package org.purplebean.kmip.codec.json.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ProtectStopDate;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProtectStopDate JSON Serialization Tests")
class ProtectStopDateJsonTest extends AbstractJsonSerializationTestSuite<ProtectStopDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<ProtectStopDate> type() {
    return ProtectStopDate.class;
  }

  @Override
  public ProtectStopDate createDefault() {
    return ProtectStopDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public ProtectStopDate createVariant() {
    return ProtectStopDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}