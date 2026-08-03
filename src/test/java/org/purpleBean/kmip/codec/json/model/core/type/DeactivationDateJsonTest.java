package org.purplebean.kmip.codec.json.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.DeactivationDate;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeactivationDate JSON Serialization Tests")
class DeactivationDateJsonTest extends AbstractJsonSerializationTestSuite<DeactivationDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<DeactivationDate> type() {
    return DeactivationDate.class;
  }

  @Override
  public DeactivationDate createDefault() {
    return DeactivationDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public DeactivationDate createVariant() {
    return DeactivationDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}