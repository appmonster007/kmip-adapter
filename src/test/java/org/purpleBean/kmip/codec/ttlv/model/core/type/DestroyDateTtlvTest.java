package org.purpleBean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DestroyDate;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DestroyDate TTLV Serialization Tests")
class DestroyDateTtlvTest extends AbstractTtlvSerializationTestSuite<DestroyDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<DestroyDate> type() {
    return DestroyDate.class;
  }

  @Override
  public DestroyDate createDefault() {

    return DestroyDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public DestroyDate createVariant() {

    return DestroyDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}
