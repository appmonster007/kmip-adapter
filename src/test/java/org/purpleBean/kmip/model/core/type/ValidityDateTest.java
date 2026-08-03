package org.purplebean.kmip.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ValidityDate Domain Tests")
class ValidityDateTest extends AbstractKmipDataTypeTestSuite<ValidityDate> {

  @Override
  protected Class<ValidityDate> type() {
    return ValidityDate.class;
  }

  @Override
  protected ValidityDate createDefault() {
    OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
    return ValidityDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.DATE_TIME;
  }
}