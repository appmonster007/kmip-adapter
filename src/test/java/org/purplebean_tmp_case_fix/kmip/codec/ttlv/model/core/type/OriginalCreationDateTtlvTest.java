package org.purplebean.kmip.codec.ttlv.model.core.type;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.OriginalCreationDate;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("OriginalCreationDate TTLV Serialization Tests")
class OriginalCreationDateTtlvTest
    extends AbstractTtlvSerializationTestSuite<OriginalCreationDate> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  public Class<OriginalCreationDate> type() {
    return OriginalCreationDate.class;
  }

  @Override
  public OriginalCreationDate createDefault() {
    return OriginalCreationDate
        .builder()
        .value(FIXED_TIME)
        .build();
  }

  @Override
  public OriginalCreationDate createVariant() {
    return OriginalCreationDate
        .builder()
        .value(FIXED_TIME.plusDays(1))
        .build();
  }
}