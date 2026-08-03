package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ValidityDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidityDate TTLV Serialization Tests")
class ValidityDateTtlvTest extends AbstractTtlvSerializationTestSuite<ValidityDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<ValidityDate> type() {
    return ValidityDate.class;
  }

  @Override
  public ValidityDate createDefault() {

    return ValidityDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public ValidityDate createVariant() {

    return ValidityDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}