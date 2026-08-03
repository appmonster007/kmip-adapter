package org.purplebean.kmip.codec.json.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ActivationDate;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ActivationDate JSON Serialization Tests")
class ActivationDateJsonTest extends AbstractJsonSerializationTestSuite<ActivationDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<ActivationDate> type() {
    return ActivationDate.class;
  }

  @Override
  public ActivationDate createDefault() {
    return ActivationDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public ActivationDate createVariant() {
    return ActivationDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}
