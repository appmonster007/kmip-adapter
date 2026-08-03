package org.purpleBean.kmip.codec.json.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LastChangeDate JSON Serialization Tests")
class LastChangeDateJsonTest extends AbstractJsonSerializationTestSuite<LastChangeDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<LastChangeDate> type() {
    return LastChangeDate.class;
  }

  @Override
  public LastChangeDate createDefault() {

    return LastChangeDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public LastChangeDate createVariant() {

    return LastChangeDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}
